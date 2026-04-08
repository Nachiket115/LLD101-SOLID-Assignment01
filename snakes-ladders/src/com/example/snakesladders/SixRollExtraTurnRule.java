package com.example.snakesladders;

public final class SixRollExtraTurnRule implements GameRule {
    @Override
    public TurnOutcome afterTurn(TurnContext context) {
        if (context.getDiceRoll().getTotal() == 6) {
            return TurnOutcome.extraTurn("Extra turn granted for rolling 6.");
        }
        return TurnOutcome.none();
    }
}
