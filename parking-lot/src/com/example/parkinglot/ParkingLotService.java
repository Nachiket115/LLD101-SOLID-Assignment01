package com.example.parkinglot;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ParkingLotService {
    private final Map<String, ParkingSlot> slots;
    private final Map<String, EntryGate> gates;
    private final Map<String, ParkingTicket> activeTickets;
    private final SlotAssignmentStrategy assignmentStrategy;
    private final BillingService billingService;
    private int ticketSequence;

    public ParkingLotService(
            List<ParkingSlot> slots,
            List<EntryGate> gates,
            SlotAssignmentStrategy assignmentStrategy,
            BillingService billingService
    ) {
        this.slots = new HashMap<>();
        this.gates = new HashMap<>();
        this.activeTickets = new HashMap<>();
        this.assignmentStrategy = Objects.requireNonNull(assignmentStrategy, "assignmentStrategy");
        this.billingService = Objects.requireNonNull(billingService, "billingService");
        this.ticketSequence = 1;
        addSlots(slots);
        addGates(gates);
    }

    public ParkingTicket park(
            VehicleDetails vehicleDetails,
            LocalDateTime entryTime,
            SlotType requestedSlotType,
            String entryGateId
    ) {
        Objects.requireNonNull(vehicleDetails, "vehicleDetails");
        Objects.requireNonNull(entryTime, "entryTime");
        Objects.requireNonNull(requestedSlotType, "requestedSlotType");
        validateGate(entryGateId);
        validateRequestedSlot(vehicleDetails, requestedSlotType);

        ParkingSlot slot = assignmentStrategy.assignSlot(slots.values(), requestedSlotType, entryGateId);
        slot.occupy();

        ParkingTicket ticket = new ParkingTicket(
                "T-" + ticketSequence++,
                vehicleDetails,
                slot.getSlotId(),
                slot.getSlotType(),
                entryTime,
                entryGateId
        );
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public AvailabilityStatus status() {
        Map<SlotType, Integer> counts = new EnumMap<>(SlotType.class);
        for (SlotType slotType : SlotType.values()) {
            counts.put(slotType, 0);
        }
        for (ParkingSlot slot : slots.values()) {
            if (slot.isAvailable()) {
                counts.put(slot.getSlotType(), counts.get(slot.getSlotType()) + 1);
            }
        }
        return new AvailabilityStatus(counts);
    }

    public ParkingBill exit(ParkingTicket parkingTicket, LocalDateTime exitTime) {
        Objects.requireNonNull(parkingTicket, "parkingTicket");
        ParkingTicket activeTicket = activeTickets.remove(parkingTicket.getTicketId());
        if (activeTicket == null) {
            throw new IllegalStateException("Ticket is not active: " + parkingTicket.getTicketId());
        }

        ParkingSlot slot = slots.get(activeTicket.getAllocatedSlotId());
        slot.release();
        return billingService.generateBill(activeTicket, exitTime);
    }

    private void validateGate(String entryGateId) {
        Objects.requireNonNull(entryGateId, "entryGateId");
        if (!gates.containsKey(entryGateId)) {
            throw new IllegalArgumentException("Invalid entry gate: " + entryGateId);
        }
    }

    private void validateRequestedSlot(VehicleDetails vehicleDetails, SlotType requestedSlotType) {
        if (!requestedSlotType.canFit(vehicleDetails.getVehicleType().getMinimumSlotType())) {
            throw new IllegalArgumentException(
                    vehicleDetails.getVehicleType() + " cannot request " + requestedSlotType + " slot."
            );
        }
    }

    private void addSlots(Collection<ParkingSlot> parkingSlots) {
        for (ParkingSlot slot : parkingSlots) {
            if (slots.put(slot.getSlotId(), slot) != null) {
                throw new IllegalArgumentException("Duplicate slot ID: " + slot.getSlotId());
            }
        }
    }

    private void addGates(Collection<EntryGate> entryGates) {
        for (EntryGate gate : entryGates) {
            if (gates.put(gate.getGateId(), gate) != null) {
                throw new IllegalArgumentException("Duplicate gate ID: " + gate.getGateId());
            }
        }
    }
}
