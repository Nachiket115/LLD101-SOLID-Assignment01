# Snakes And Ladders Design

## 1. Requirement Summary
We need to design a turn-based multiplayer Snakes and Ladders game with:

- multiple players in fixed turn order
- configurable dice support
- configurable board size
- snakes and ladders mapping
- exact-win rule
- clean extensibility for future game rules
- testability through mockable dice

## 2. Design Goals
This design focuses on three things:

- mapping gameplay mechanics cleanly to classes
- keeping turn execution simple and efficient
- making the game easy to extend with new rules

## 3. Core Classes

### `Game`
- controls the game loop
- manages player turns
- delegates movement and rule application
- declares the winner

### `Board`
- stores board size
- stores snakes and ladders
- validates board setup
- resolves jumps in O(1) using a map

### `Player`
- stores player identity
- stores current position

### `Dice`
- abstraction for rolling values
- mockable for tests

### `StandardDice`
- concrete random dice implementation
- supports configurable number of dice and faces

### `FixedDice`
- deterministic dice for predictable testing/demo

### `Jump`
- common abstraction for board transitions

### `Snake`
- represents downward movement

### `Ladder`
- represents upward movement

### `GameRule`
- extension point for custom rules
- examples: exact-win handling, extra turn on rolling 6

### `ExactWinRule`
- enforces the rule that a move beyond the final cell is ignored

### `SixRollExtraTurnRule`
- optional bonus rule for extensibility

## 4. UML Style Diagram
```text
+------------------------------------------------------+
|                        Dice                          |
+------------------------------------------------------+
| + roll(): DiceRoll                                   |
+------------------------------------------------------+
                 ^                         ^
                 |                         |
     implements  |                         | implements
                 |                         |
+-------------------------------+   +-------------------------------+
|         StandardDice          |   |          FixedDice            |
+-------------------------------+   +-------------------------------+
| - diceCount: int              |   | - rolls: Queue<Integer>       |
| - faces: int                  |   +-------------------------------+
| - random: Random              |   | + roll(): DiceRoll            |
+-------------------------------+   +-------------------------------+
| + roll(): DiceRoll            |
+-------------------------------+

+------------------------------------------------------+
|                      GameRule                        |
+------------------------------------------------------+
| + afterTurn(ctx: TurnContext): TurnOutcome           |
+------------------------------------------------------+
                 ^                         ^
                 |                         |
                 |                         |
+-------------------------------+   +-------------------------------+
|        ExactWinRule           |   |     SixRollExtraTurnRule      |
+-------------------------------+   +-------------------------------+

+------------------------------------------------------+
|                        Game                          |
+------------------------------------------------------+
| - board: Board                                       |
| - players: List<Player>                              |
| - dice: Dice                                         |
| - rules: List<GameRule>                              |
| - currentTurnIndex: int                              |
+------------------------------------------------------+
| + play(): Player                                     |
| + playTurn(): TurnResult                             |
+------------------------------------------------------+
      | has-a                 | has-many         | uses
      v                       v                  v
+-------------+       +----------------+   +----------------+
|    Board    |       |    Player      |   |  TurnContext   |
+-------------+       +----------------+   +----------------+
| - size:int  |       | - id:String    |   | turn snapshot  |
| - jumps:Map |       | - position:int |   +----------------+
+-------------+       +----------------+
| + resolve() |       | + moveTo()     |
| + isFinal() |
+-------------+
      |
      | has-many
      v
+------------------------------------------------------+
|                        Jump                          |
+------------------------------------------------------+
| - start: int                                         |
| - end: int                                           |
| + isValidFor(boardSize: int): boolean                |
+------------------------------------------------------+
                 ^                         ^
                 |                         |
                 |                         |
+-------------------------------+   +-------------------------------+
|            Snake              |   |            Ladder             |
+-------------------------------+   +-------------------------------+
| head > tail                   |   | start < end                   |
+-------------------------------+   +-------------------------------+
```

## 5. How Requirements Map To Design

### Player actions
- `Game` picks the current player
- `Dice` generates the roll
- `Board` resolves snakes and ladders
- `Player` updates position

### Game rules
- board movement is handled by `Board`
- winning and overshoot logic are handled through `GameRule`
- future rules can be added without changing the main game loop too much

### Scoring / winner handling
Snakes and Ladders does not use a score in the traditional sense.
Instead, progress on the board acts as the player's score.
The winner is the first player to reach the final cell exactly.

### Extensibility
The design already supports:
- multiple dice
- custom board sizes
- custom deterministic dice for testing
- optional extra-turn rule

## 6. Design Decisions

### Why `Dice` is an interface
This makes the game testable.
We can inject `FixedDice` during testing and `StandardDice` during normal gameplay.

### Why `Board` uses a map for jumps
This gives O(1) lookup when checking whether a cell has a snake or ladder.

### Why snakes and ladders share `Jump`
Both are board transitions with a start and end position.
The shared abstraction removes duplication while still allowing validation differences.

### Why rules are separate
Rules often change in interviews and assignments.
Keeping them behind a `GameRule` interface makes the design easier to modify.

## 7. Edge Cases Covered
- less than 2 players is invalid
- snake head at 1 is invalid
- snake tail at board end is invalid
- ladder start at 1 or end at final cell is invalid
- overlapping snake head and ladder start is invalid
- overshoot move is ignored
- exact snake head landing triggers immediate movement
- chain movement is supported because the board keeps resolving until stable

## 8. Files
- `App.java` - runnable demo
- `Board.java` - board setup and jump resolution
- `Game.java` - turn management and winner handling
- `Player.java` - player state
- `Dice.java` - dice abstraction
- `StandardDice.java` - random configurable dice
- `FixedDice.java` - deterministic dice
- `DiceRoll.java` - roll result model
- `Jump.java` - common board transition abstraction
- `Snake.java` - snake model
- `Ladder.java` - ladder model
- `GameRule.java` - extension interface
- `ExactWinRule.java` - exact win behavior
- `SixRollExtraTurnRule.java` - optional extra-turn rule
- `TurnContext.java` - turn data passed to rules
- `TurnOutcome.java` - rule response object
- `TurnResult.java` - per-turn output model

## 9. Build And Run
```bash
cd snakes-ladders/src
javac com/example/snakesladders/*.java
java com.example.snakesladders.App
```

## 10. Quick Interview Summary
“We need to design a turn-based multiplayer Snakes and Ladders game with a configurable board, snakes and ladders mapping, dice-based movement, and a winning condition of reaching exactly the last cell. The system should be extensible for multiple dice and custom rules.”
