package com.example.parkinglot;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ParkingTicket {
    private final String ticketId;
    private final VehicleDetails vehicleDetails;
    private final String allocatedSlotId;
    private final SlotType allocatedSlotType;
    private final LocalDateTime entryTime;
    private final String entryGateId;

    public ParkingTicket(
            String ticketId,
            VehicleDetails vehicleDetails,
            String allocatedSlotId,
            SlotType allocatedSlotType,
            LocalDateTime entryTime,
            String entryGateId
    ) {
        this.ticketId = Objects.requireNonNull(ticketId, "ticketId");
        this.vehicleDetails = Objects.requireNonNull(vehicleDetails, "vehicleDetails");
        this.allocatedSlotId = Objects.requireNonNull(allocatedSlotId, "allocatedSlotId");
        this.allocatedSlotType = Objects.requireNonNull(allocatedSlotType, "allocatedSlotType");
        this.entryTime = Objects.requireNonNull(entryTime, "entryTime");
        this.entryGateId = Objects.requireNonNull(entryGateId, "entryGateId");
    }

    public String getTicketId() {
        return ticketId;
    }

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public String getAllocatedSlotId() {
        return allocatedSlotId;
    }

    public SlotType getAllocatedSlotType() {
        return allocatedSlotType;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getEntryGateId() {
        return entryGateId;
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "ticketId='" + ticketId + '\''
                + ", vehicle=" + vehicleDetails
                + ", slot='" + allocatedSlotId + '\''
                + ", slotType=" + allocatedSlotType
                + ", entryTime=" + entryTime
                + '}';
    }
}
