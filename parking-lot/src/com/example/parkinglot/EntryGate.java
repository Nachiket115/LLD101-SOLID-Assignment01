package com.example.parkinglot;

import java.util.Objects;

public final class EntryGate {
    private final String gateId;

    public EntryGate(String gateId) {
        this.gateId = Objects.requireNonNull(gateId, "gateId");
    }

    public String getGateId() {
        return gateId;
    }
}
