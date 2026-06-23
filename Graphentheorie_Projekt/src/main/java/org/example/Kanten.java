package org.example;

public class Kanten {

    private final Knoten ziel;
    private final double gewicht;

    public Kanten(Knoten ziel, double gewicht) {
        this.ziel = ziel;
        this.gewicht = gewicht;
    }

    public Knoten getZiel() {
        return ziel;
    }

    public double getGewicht() {
        return gewicht;
    }

    @Override
    public String toString() {
        return "→ " + ziel.getName() + " (Gewicht: " + gewicht + ")";
    }
}
