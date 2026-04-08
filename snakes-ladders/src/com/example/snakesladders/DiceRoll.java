package com.example.snakesladders;

import java.util.List;
import java.util.Objects;

public final class DiceRoll {
    private final List<Integer> values;
    private final int total;

    public DiceRoll(List<Integer> values) {
        this.values = List.copyOf(Objects.requireNonNull(values, "values"));
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException("At least one die value is required.");
        }
        int sum = 0;
        for (int value : this.values) {
            sum += value;
        }
        this.total = sum;
    }

    public List<Integer> getValues() {
        return values;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return values + " => " + total;
    }
}
