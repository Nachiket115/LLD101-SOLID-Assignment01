package com.example.snakesladders;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public final class FixedDice implements Dice {
    private final Queue<Integer> rolls;

    public FixedDice(List<Integer> rolls) {
        Objects.requireNonNull(rolls, "rolls");
        if (rolls.isEmpty()) {
            throw new IllegalArgumentException("Fixed dice needs at least one roll.");
        }
        this.rolls = new ArrayDeque<>(rolls);
    }

    @Override
    public DiceRoll roll() {
        Integer value = rolls.poll();
        if (value == null) {
            throw new IllegalStateException("No more fixed dice rolls available.");
        }
        return new DiceRoll(List.of(value));
    }
}
