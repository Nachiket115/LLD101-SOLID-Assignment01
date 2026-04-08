package com.example.snakesladders;

public final class TurnOutcome {
    private final boolean grantExtraTurn;
    private final String note;

    private TurnOutcome(boolean grantExtraTurn, String note) {
        this.grantExtraTurn = grantExtraTurn;
        this.note = note;
    }

    public static TurnOutcome none() {
        return new TurnOutcome(false, "");
    }

    public static TurnOutcome extraTurn(String note) {
        return new TurnOutcome(true, note);
    }

    public boolean shouldGrantExtraTurn() {
        return grantExtraTurn;
    }

    public String getNote() {
        return note;
    }
}
