package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final ArrayList<Knoten> knotenListe = new ArrayList<>();
    private final List<String[]> matrix = new ArrayList<>();

    public List<Knoten> getKnotenListe() {
        return this.knotenListe;
    }

    public void CSVimport() throws GraphException {

        matrix.clear();

        // ---------- 1. Adjazenzmatrix einlesen ----------
        try (BufferedReader reader = new BufferedReader(new FileReader("Mein Graph.csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                matrix.add(line.split(";"));
            }

        } catch (FileNotFoundException e) {
            throw new GraphException("Datei 'MeinGraph.csv' nicht gefunden.");
        } catch (IOException e) {
            throw new GraphException("Fehler beim Einlesen der Datei 'MeinGraph.csv'.");
        }

        // ---------- 2. Knoten erzeugen ----------
        knotenListe.clear();

        for (int i = 0; i < matrix.size(); i++) {
            knotenListe.add(new Knoten("Knoten" + i));
        }

        // ---------- 3. Kanten erzeugen ----------
        for (int i = 0; i < matrix.size(); i++) {

            Knoten start = knotenListe.get(i);
            String[] row = matrix.get(i);

            for (int j = 0; j < row.length; j++) {

                if (row[j].trim().equals("1")) {

                    Knoten ziel = knotenListe.get(j);

                    start.addVerbindung(ziel, 1.0);
                }
            }
        }
    }

    // ---------- MATRIX AUSGEBEN ----------
    public void ausgabeMatrix() {
        System.out.println("Adjazenzmatrix:");
        for (String[] row : matrix) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // ---------- GRAPH AUSGEBEN ----------
    public void ausgabeCSV() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {

        if (knotenListe.isEmpty()) return "Netz ist leer.";

        StringBuilder sb = new StringBuilder("Verbindung der Knoten zueinander durch Kanten:\n");

        for (Knoten k : knotenListe) {

            sb.append(k.getName()).append(" -> ");

            if (k.getNachbarn().isEmpty()) {
                sb.append("keine Kanten");
            } else {
                for (Kanten kanten : k.getNachbarn()) {
                    sb.append(kanten.getZiel().getName()).append(", ");
                }
                sb.setLength(sb.length() - 2);
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
