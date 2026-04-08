package com.example.snakesladders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Game {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final List<GameRule> rules;
    private int currentTurnIndex;
    private Player winner;

    public Game(Board board, List<Player> players, Dice dice, List<GameRule> rules) {
        this.board = Objects.requireNonNull(board, "board");
        this.players = List.copyOf(Objects.requireNonNull(players, "players"));
        this.dice = Objects.requireNonNull(dice, "dice");
        this.rules = new ArrayList<>(Objects.requireNonNull(rules, "rules"));
        validatePlayers(this.players);
        this.currentTurnIndex = 0;
    }

    public Player play() {
        while (winner == null) {
            TurnResult result = playTurn();
            System.out.println(result);
        }
        return winner;
    }

    public TurnResult playTurn() {
        if (winner != null) {
            throw new IllegalStateException("Game already has a winner.");
        }

        Player player = players.get(currentTurnIndex);
        DiceRoll roll = dice.roll();
        int startPosition = player.getPosition();
        int attemptedPosition = startPosition + roll.getTotal();
        boolean overshot = attemptedPosition > board.getSize();

        int landingPosition = overshot ? startPosition : attemptedPosition;
        int resolvedPosition = overshot ? startPosition : board.resolvePosition(landingPosition);
        player.moveTo(resolvedPosition);

        TurnContext context = new TurnContext(
                board,
                player,
                roll,
                startPosition,
                attemptedPosition,
                resolvedPosition,
                overshot
        );

        boolean extraTurn = false;
        String note = "";
        for (GameRule rule : rules) {
            TurnOutcome outcome = rule.afterTurn(context);
            if (outcome.shouldGrantExtraTurn()) {
                extraTurn = true;
            }
            if (!outcome.getNote().isEmpty()) {
                note = note.isEmpty() ? outcome.getNote() : note + " " + outcome.getNote();
            }
        }

        boolean won = board.isFinalCell(player.getPosition());
        if (won) {
            winner = player;
        }

        if (!won && !extraTurn) {
            currentTurnIndex = (currentTurnIndex + 1) % players.size();
        }

        return new TurnResult(player, roll, startPosition, player.getPosition(), overshot, won, extraTurn, note);
    }

    private void validatePlayers(List<Player> players) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("At least two players are required.");
        }
    }
}
