package com.example.snakesladders;

import java.util.List;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Board board = new Board(
                100,
                List.of(
                        new Snake(17, 7),
                        new Snake(54, 34),
                        new Snake(62, 19),
                        new Snake(98, 79)
                ),
                List.of(
                        new Ladder(3, 22),
                        new Ladder(5, 8),
                        new Ladder(11, 26),
                        new Ladder(20, 29),
                        new Ladder(27, 84)
                )
        );

        Game game = new Game(
                board,
                List.of(new Player("Aman"), new Player("Riya")),
                new FixedDice(List.of(
                        3, 4, 5, 6, 1, 4, 2, 4, 3, 4, 2, 4
                )),
                List.of(
                        new ExactWinRule(),
                        new SixRollExtraTurnRule()
                )
        );

        Player winner = game.play();
        System.out.println("Winner: " + winner.getId());
    }
}
