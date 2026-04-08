package com.example.pen;

import java.util.Objects;

public final class Ink {
    private final String color;
    private int levelPercent;

    public Ink(String color, int levelPercent) {
        this.color = Objects.requireNonNull(color, "color");
        if (levelPercent < 0 || levelPercent > 100) {
            throw new IllegalArgumentException("Ink level must be between 0 and 100.");
        }
        this.levelPercent = levelPercent;
    }

    public String getColor() {
        return color;
    }

    public int getLevelPercent() {
        return levelPercent;
    }

    public boolean hasEnough(int requiredPercent) {
        return requiredPercent >= 0 && levelPercent >= requiredPercent;
    }

    public void consume(int usedPercent) {
        if (usedPercent < 0) {
            throw new IllegalArgumentException("Used percent cannot be negative.");
        }
        if (!hasEnough(usedPercent)) {
            throw new IllegalStateException("Not enough ink.");
        }
        levelPercent -= usedPercent;
    }
}
