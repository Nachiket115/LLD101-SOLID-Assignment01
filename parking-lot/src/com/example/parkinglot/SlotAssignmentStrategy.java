package com.example.parkinglot;

import java.util.Collection;

public interface SlotAssignmentStrategy {
    ParkingSlot assignSlot(Collection<ParkingSlot> slots, SlotType requestedSlotType, String entryGateId);
}
