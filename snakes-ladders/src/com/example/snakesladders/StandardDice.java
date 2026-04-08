package com.example.snakesladders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class StandardDice implements Dice {
    private final int diceCount;
    private final int faces;
    private final Random random;

    public StandardDice(int diceCount, int faces) {
        this(diceCount, faces, new Random());
    }

    public StandardDice(int diceCount, int faces, Random random) {
        if (diceCount <= 0) {
            throw new IllegalArgumentException("Dice count must be positive.");
        }
        if (faces <= 1) {
            throw new IllegalArgumentException("Dice faces must be greater than 1.");
        }
        this.diceCount = diceCount;
        this.faces = faces;
        this.random = random;
    }

    @Override
    public DiceRoll roll() {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < diceCount; i++) {
            values.add(random.nextInt(faces) + 1);
        }
        return new DiceRoll(values);
    }
}
