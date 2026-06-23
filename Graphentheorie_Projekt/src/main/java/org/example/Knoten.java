package org.example;

import java.util.ArrayList;
import java.util.List;

public class Knoten {

    private String knotenname;
    private List<Kanten> nachbarn = new ArrayList<>();

    public Knoten(String name) {
        this.knotenname = name;
    }

    public String getName() {
        return knotenname;
    }

    public List<Kanten> getNachbarn() {
        return nachbarn;
    }

    public void addVerbindung(Knoten ziel, double gewicht) {
        nachbarn.add(new Kanten(ziel, gewicht));
    }

    @Override
    public String toString() {
        return knotenname;
    }
}
