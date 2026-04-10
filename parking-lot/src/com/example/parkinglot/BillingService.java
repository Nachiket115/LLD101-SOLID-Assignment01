package com.example.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class BillingService {
    private final Map<SlotType, Double> hourlyRates;

    public BillingService(Map<SlotType, Double> hourlyRates) {
        this.hourlyRates = new EnumMap<>(Objects.requireNonNull(hourlyRates, "hourlyRates"));
        for (SlotType slotType : SlotType.values()) {
            if (!this.hourlyRates.containsKey(slotType)) {
                throw new IllegalArgumentException("Missing hourly rate for " + slotType);
            }
        }
    }

    public ParkingBill generateBill(ParkingTicket ticket, LocalDateTime exitTime) {
        Objects.requireNonNull(ticket, "ticket");
        Objects.requireNonNull(exitTime, "exitTime");
        if (exitTime.isBefore(ticket.getEntryTime())) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }

        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long billedHours = Math.max(1, (long) Math.ceil(minutes / 60.0));
        double amount = billedHours * hourlyRates.get(ticket.getAllocatedSlotType());

        return new ParkingBill(
                ticket.getTicketId(),
                ticket.getAllocatedSlotType(),
                ticket.getEntryTime(),
                exitTime,
                billedHours,
                amount
        );
    }
}
