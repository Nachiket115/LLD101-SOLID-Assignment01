package com.example.snakesladders;

public final class Snake extends Jump {
    public Snake(int head, int tail) {
        super(head, tail);
    }

    @Override
    public boolean isValidFor(int boardSize) {
        return getStart() > 1 && getEnd() < boardSize && getStart() > getEnd();
    }
}
