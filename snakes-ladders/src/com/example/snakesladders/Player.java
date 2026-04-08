package com.example.snakesladders;

import java.util.Objects;

public final class Player {
    private final String id;
    private int position;

    public Player(String id) {
        this.id = Objects.requireNonNull(id, "id");
        this.position = 0;
    }

    public String getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void moveTo(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position cannot be negative.");
        }
        this.position = position;
    }
}
