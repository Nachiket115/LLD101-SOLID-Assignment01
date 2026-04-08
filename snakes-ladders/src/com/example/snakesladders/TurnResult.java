package com.example.snakesladders;

public final class TurnResult {
    private final Player player;
    private final DiceRoll roll;
    private final int startPosition;
    private final int endPosition;
    private final boolean overshot;
    private final boolean won;
    private final boolean extraTurn;
    private final String note;

    public TurnResult(
            Player player,
            DiceRoll roll,
            int startPosition,
            int endPosition,
            boolean overshot,
            boolean won,
            boolean extraTurn,
            String note
    ) {
        this.player = player;
        this.roll = roll;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.overshot = overshot;
        this.won = won;
        this.extraTurn = extraTurn;
        this.note = note;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isExtraTurn() {
        return extraTurn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(player.getId())
                .append(" rolled ")
                .append(roll)
                .append(" | ")
                .append(startPosition)
                .append(" -> ")
                .append(endPosition);

        if (overshot) {
            builder.append(" | move ignored (overshoot)");
        }
        if (!note.isEmpty()) {
            builder.append(" | ").append(note);
        }
        if (won) {
            builder.append(" | WINNER");
        }
        return builder.toString();
    }
}
