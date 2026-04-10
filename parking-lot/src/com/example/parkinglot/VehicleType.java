package com.example.parkinglot;

public enum VehicleType {
    TWO_WHEELER(SlotType.SMALL),
    CAR(SlotType.MEDIUM),
    BUS(SlotType.LARGE);

    private final SlotType minimumSlotType;

    VehicleType(SlotType minimumSlotType) {
        this.minimumSlotType = minimumSlotType;
    }

    public SlotType getMinimumSlotType() {
        return minimumSlotType;
    }
}
