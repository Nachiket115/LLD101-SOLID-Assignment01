package com.example.pen;

import java.util.Objects;

public final class Refill {
    private final PenType penType;
    private final double tipSizeMm;
    private final Ink ink;

    public Refill(PenType penType, double tipSizeMm, Ink ink) {
        this.penType = Objects.requireNonNull(penType, "penType");
        this.ink = Objects.requireNonNull(ink, "ink");
        if (tipSizeMm <= 0) {
            throw new IllegalArgumentException("Tip size must be positive.");
        }
        this.tipSizeMm = tipSizeMm;
    }

    public PenType getPenType() {
        return penType;
    }

    public double getTipSizeMm() {
        return tipSizeMm;
    }

    public Ink getInk() {
        return ink;
    }

    public boolean canWrite(String text) {
        return ink.hasEnough(requiredInk(text));
    }

    public void useFor(String text) {
        ink.consume(requiredInk(text));
    }

    private int requiredInk(String text) {
        String safeText = Objects.requireNonNull(text, "text").trim();
        if (safeText.isEmpty()) {
            return 0;
        }

        int usage = Math.max(1, safeText.length() / 2);
        return Math.min(usage, 100);
    }
}
