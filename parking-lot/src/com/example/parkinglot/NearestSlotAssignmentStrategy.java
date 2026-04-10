package com.example.parkinglot;

import java.util.Collection;
import java.util.Comparator;

public final class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy {
    @Override
    public ParkingSlot assignSlot(Collection<ParkingSlot> slots, SlotType requestedSlotType, String entryGateId) {
        return slots.stream()
                .filter(ParkingSlot::isAvailable)
                .filter(slot -> slot.getSlotType().canFit(requestedSlotType))
                .min(Comparator
                        .comparingInt((ParkingSlot slot) -> slot.distanceFrom(entryGateId))
                        .thenComparingInt(slot -> slot.getSlotType().ordinal())
                        .thenComparing(ParkingSlot::getSlotId))
                .orElseThrow(() -> new IllegalStateException("No compatible slot available."));
    }
}
