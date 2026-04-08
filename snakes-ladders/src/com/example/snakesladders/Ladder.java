package com.example.snakesladders;

public final class Ladder extends Jump {
    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public boolean isValidFor(int boardSize) {
        return getStart() > 1 && getEnd() < boardSize && getStart() < getEnd();
    }
}
