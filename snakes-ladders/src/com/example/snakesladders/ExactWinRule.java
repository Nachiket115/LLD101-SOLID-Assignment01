package com.example.snakesladders;

public final class ExactWinRule implements GameRule {
    @Override
    public TurnOutcome afterTurn(TurnContext context) {
        return TurnOutcome.none();
    }
}
