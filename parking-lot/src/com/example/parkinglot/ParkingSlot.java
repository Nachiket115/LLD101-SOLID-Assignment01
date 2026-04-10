package com.example.parkinglot;

import java.util.Map;
import java.util.Objects;

public final class ParkingSlot {
    private final String slotId;
    private final int level;
    private final SlotType slotType;
    private final Map<String, Integer> distanceByGate;
    private boolean available;

    public ParkingSlot(String slotId, int level, SlotType slotType, Map<String, Integer> distanceByGate) {
        this.slotId = Objects.requireNonNull(slotId, "slotId");
        this.slotType = Objects.requireNonNull(slotType, "slotType");
        this.distanceByGate = Map.copyOf(Objects.requireNonNull(distanceByGate, "distanceByGate"));
        if (level < 0) {
            throw new IllegalArgumentException("Level cannot be negative.");
        }
        if (this.distanceByGate.isEmpty()) {
            throw new IllegalArgumentException("Slot must have at least one gate distance.");
        }
        this.level = level;
        this.available = true;
    }

    public String getSlotId() {
        return slotId;
    }

    public int getLevel() {
        return level;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isAvailable() {
        return available;
    }

    public int distanceFrom(String gateId) {
        Integer distance = distanceByGate.get(gateId);
        if (distance == null) {
            throw new IllegalArgumentException("No distance configured from gate " + gateId + " to slot " + slotId);
        }
        return distance;
    }

    public void occupy() {
        if (!available) {
            throw new IllegalStateException("Slot is already occupied: " + slotId);
        }
        available = false;
    }

    public void release() {
        if (available) {
            throw new IllegalStateException("Slot is already available: " + slotId);
        }
        available = true;
    }
}
