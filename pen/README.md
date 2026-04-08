# Pen Design

## 1. Problem Statement
The goal of this assignment is to design a simple object-oriented model of a pen.
The pen should be able to:

- write text
- consume ink while writing
- stay capped or uncapped
- support refill replacement
- expose a clean and maintainable design

## 2. Design Approach
This solution uses composition to model a real pen as a set of smaller parts instead of putting all logic into one class.

The central idea is:
- a `Pen` is the main object used by the client
- a `Pen` has a `PenBody`
- a `Pen` has a `Refill`
- a `Refill` contains `Ink`
- `Pen` implements the `WritingInstrument` interface

This keeps the design modular, realistic, and easy to extend.

## 3. UML Style Class Diagram
```text
+--------------------------------------------------+
|                WritingInstrument                 |
+--------------------------------------------------+
| + write(text: String): String                    |
+--------------------------------------------------+
                    ^
                    |
                    | implements
                    |
+--------------------------------------------------+
|                       Pen                        |
+--------------------------------------------------+
| - body: PenBody                                  |
| - refill: Refill                                 |
| - capped: boolean                                |
+--------------------------------------------------+
| + open(): void                                   |
| + close(): void                                  |
| + replaceRefill(refill: Refill): void            |
| + describe(): String                             |
| + write(text: String): String                    |
| + inkLevel(): int                                |
+--------------------------------------------------+
          | has-a                         | has-a
          |                               |
          v                               v
+---------------------------+   +---------------------------+
|         PenBody           |   |          Refill           |
+---------------------------+   +---------------------------+
| - brand: String           |   | - penType: PenType        |
| - model: String           |   | - tipSizeMm: double       |
| - material: String        |   | - ink: Ink                |
+---------------------------+   +---------------------------+
| + getBrand(): String      |   | + getPenType(): PenType   |
| + getModel(): String      |   | + getTipSizeMm(): double  |
| + getMaterial(): String   |   | + getInk(): Ink           |
+---------------------------+   | + canWrite(text): boolean |
                                | + useFor(text): void      |
                                +---------------------------+
                                               |
                                               | contains
                                               v
                                +---------------------------+
                                |            Ink            |
                                +---------------------------+
                                | - color: String           |
                                | - levelPercent: int       |
                                +---------------------------+
                                | + getColor(): String      |
                                | + getLevelPercent(): int  |
                                | + hasEnough(int): boolean |
                                | + consume(int): void      |
                                +---------------------------+

+---------------------------+
|         PenType           |
+---------------------------+
| BALL                      |
| GEL                       |
| FOUNTAIN                  |
+---------------------------+
```

## 4. Class Responsibilities

### `WritingInstrument`
This is a simple abstraction for any object that can write.
It improves flexibility because different writing tools can follow the same contract.

### `Pen`
This is the main domain class.
It controls pen behavior such as opening, closing, writing, and replacing the refill.
It does not directly manage low-level ink calculations. Instead, it delegates that work to the refill and ink objects.

### `PenBody`
This class stores the physical identity of the pen such as:
- brand
- model
- material

It keeps descriptive information separate from writing behavior.

### `Refill`
This class represents the replaceable writing unit inside the pen.
It knows:
- the pen type
- the tip size
- the ink currently available

It is responsible for deciding whether enough ink is available and for reducing the ink level when writing happens.

### `Ink`
This class stores the ink properties.
It tracks:
- ink color
- remaining ink percentage

It also validates consumption so the system never goes below zero ink.

### `PenType`
This enum represents the category of pen.
Using an enum makes the type safe and avoids invalid string values.

## 5. Object Relationships
- `Pen` implements `WritingInstrument`
- `Pen` has a `PenBody`
- `Pen` has a `Refill`
- `Refill` contains `Ink`
- `Refill` uses `PenType`

These are all strong object-oriented relationships based on composition.
This is a good design because a pen is naturally made of parts, and those parts can be changed independently.

## 6. How the Writing Flow Works
When the client calls `write(text)` on the pen:

1. The pen first checks whether it is capped.
2. If the pen is capped, writing is rejected.
3. If the pen is open, the refill checks whether enough ink is available.
4. If enough ink exists, ink is consumed.
5. The pen returns a success message showing the ink color and written text.

This flow keeps validation clean and prevents invalid states.

## 7. Why This Is Good OOP Design

### Encapsulation
Each class hides its own internal data and rules.
For example, only `Ink` controls how ink level changes.

### Single Responsibility Principle
Every class has one focused job:
- `Pen` handles overall pen behavior
- `Refill` handles writing resource usage
- `Ink` handles ink state
- `PenBody` handles identity details

### Composition Over Monolithic Design
Instead of one giant class doing everything, the system is broken into meaningful parts.
This makes the design easier to understand and maintain.

### Extensibility
If needed later, the design can be extended with:
- different pen categories
- different ink usage strategies
- marker or pencil classes implementing `WritingInstrument`

### Reusability
`Ink`, `Refill`, and `PenBody` can be reused in new designs without changing the rest of the system.

## 8. Files in This Assignment
- `App.java` - demo program
- `WritingInstrument.java` - writing abstraction
- `Pen.java` - main pen behavior
- `PenBody.java` - physical pen details
- `Refill.java` - refill and writing support
- `Ink.java` - ink information and consumption
- `PenType.java` - enum for pen categories

## 9. Build And Run
```bash
cd pen/src
javac com/example/pen/*.java
java com.example.pen.App
```

## 10. Sample Output
```text
Brand: LLD Notes | Model: SmoothFlow | Material: Plastic
Type: GEL | Tip: 0.7mm | Ink: Blue (60%)
Pen cap removed.
Writing with Blue ink: Low level design starts with good objects.
Ink remaining: 39%
Writing with Blue ink: This sentence is intentionally long so the first refill runs out quickly.
Refill replaced.
Writing with Black ink: New refill installed and ready.
Ink remaining: 10%
Pen capped.
```

## 11. Conclusion
This pen design is a clean example of object-oriented modeling.
It uses abstraction, encapsulation, composition, and clear class responsibilities to represent a real-world object in a simple but maintainable way.
