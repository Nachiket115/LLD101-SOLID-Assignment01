package com.example.pen;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Pen pen = new Pen(
                new PenBody("LLD Notes", "SmoothFlow", "Plastic"),
                new Refill(PenType.GEL, 0.7, new Ink("Blue", 60))
        );

        System.out.println(pen.describe());

        pen.open();
        System.out.println(pen.write("Low level design starts with good objects."));
        System.out.println("Ink remaining: " + pen.inkLevel() + "%");

        System.out.println(pen.write("This sentence is intentionally long so the first refill runs out quickly."));

        pen.replaceRefill(new Refill(PenType.GEL, 0.7, new Ink("Black", 25)));
        System.out.println(pen.write("New refill installed and ready."));
        System.out.println("Ink remaining: " + pen.inkLevel() + "%");

        pen.close();
    }
}
