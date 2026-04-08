package com.example.snakesladders;

public final class TurnContext {
    private final Board board;
    private final Player player;
    private final DiceRoll diceRoll;
    private final int startPosition;
    private final int attemptedPosition;
    private final int resolvedPosition;
    private final boolean overshot;

    public TurnContext(
            Board board,
            Player player,
            DiceRoll diceRoll,
            int startPosition,
            int attemptedPosition,
            int resolvedPosition,
            boolean overshot
    ) {
        this.board = board;
        this.player = player;
        this.diceRoll = diceRoll;
        this.startPosition = startPosition;
        this.attemptedPosition = attemptedPosition;
        this.resolvedPosition = resolvedPosition;
        this.overshot = overshot;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public DiceRoll getDiceRoll() {
        return diceRoll;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getAttemptedPosition() {
        return attemptedPosition;
    }

    public int getResolvedPosition() {
        return resolvedPosition;
    }

    public boolean isOvershot() {
        return overshot;
    }
}
