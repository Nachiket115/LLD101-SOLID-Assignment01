package com.example.pen;

import java.util.Objects;

public final class Pen implements WritingInstrument {
    private final PenBody body;
    private Refill refill;
    private boolean capped = true;

    public Pen(PenBody body, Refill refill) {
        this.body = Objects.requireNonNull(body, "body");
        this.refill = Objects.requireNonNull(refill, "refill");
    }

    public void open() {
        capped = false;
        System.out.println("Pen cap removed.");
    }

    public void close() {
        capped = true;
        System.out.println("Pen capped.");
    }

    public void replaceRefill(Refill refill) {
        this.refill = Objects.requireNonNull(refill, "refill");
        System.out.println("Refill replaced.");
    }

    public String describe() {
        return "Brand: " + body.getBrand() + " | Model: " + body.getModel() + " | Material: " + body.getMaterial()
                + System.lineSeparator()
                + "Type: " + refill.getPenType() + " | Tip: " + refill.getTipSizeMm() + "mm | Ink: "
                + refill.getInk().getColor() + " (" + refill.getInk().getLevelPercent() + "%)";
    }

    @Override
    public String write(String text) {
        Objects.requireNonNull(text, "text");

        if (capped) {
            return "Cannot write while the pen is capped.";
        }
        if (!refill.canWrite(text)) {
            return "Cannot write. Refill does not have enough ink.";
        }

        refill.useFor(text);
        return "Writing with " + refill.getInk().getColor() + " ink: " + text;
    }

    public int inkLevel() {
        return refill.getInk().getLevelPercent();
    }
}
