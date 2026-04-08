package com.example.snakesladders;

public interface GameRule {
    TurnOutcome afterTurn(TurnContext context);
}
