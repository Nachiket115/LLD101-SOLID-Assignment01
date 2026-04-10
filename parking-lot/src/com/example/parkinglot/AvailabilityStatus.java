package com.example.parkinglot;

import java.util.EnumMap;
import java.util.Map;

public final class AvailabilityStatus {
    private final Map<SlotType, Integer> availableByType;

    public AvailabilityStatus(Map<SlotType, Integer> availableByType) {
        this.availableByType = new EnumMap<>(availableByType);
    }

    public int availableCount(SlotType slotType) {
        return availableByType.getOrDefault(slotType, 0);
    }

    @Override
    public String toString() {
        return availableByType.toString();
    }
}
