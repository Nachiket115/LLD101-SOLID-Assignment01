package com.example.parkinglot;

import java.time.LocalDateTime;

public final class ParkingBill {
    private final String ticketId;
    private final SlotType slotType;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final long billedHours;
    private final double amount;

    public ParkingBill(
            String ticketId,
            SlotType slotType,
            LocalDateTime entryTime,
            LocalDateTime exitTime,
            long billedHours,
            double amount
    ) {
        this.ticketId = ticketId;
        this.slotType = slotType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.billedHours = billedHours;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bill{"
                + "ticketId='" + ticketId + '\''
                + ", slotType=" + slotType
                + ", billedHours=" + billedHours
                + ", amount=" + amount
                + '}';
    }
}
