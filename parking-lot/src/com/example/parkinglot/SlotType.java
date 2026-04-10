package com.example.parkinglot;

public enum SlotType {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int sizeRank;

    SlotType(int sizeRank) {
        this.sizeRank = sizeRank;
    }

    public boolean canFit(SlotType requestedType) {
        return sizeRank >= requestedType.sizeRank;
    }
}
