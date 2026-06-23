package org.example;

import java.util.*;

public class DistanceCalculator {

    private final Graph graph;

    public DistanceCalculator(Graph graph) {
        this.graph = graph;
    }

    // ---------------------------------------------------------
    // DISTANZEN EINES KNOTENS (BFS)
    // ---------------------------------------------------------
    public int[] berechneDistanzen(Knoten start) {

        int n = graph.getKnotenListe().size();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        Queue<Knoten> queue = new LinkedList<>();
        queue.add(start);
        dist[graph.getKnotenListe().indexOf(start)] = 0;

        while (!queue.isEmpty()) {

            Knoten aktueller = queue.poll();
            int indexAktuell = graph.getKnotenListe().indexOf(aktueller);

            for (Kanten k : aktueller.getNachbarn()) {
                Knoten nachbar = k.getZiel();
                int indexNachbar = graph.getKnotenListe().indexOf(nachbar);

                if (dist[indexNachbar] == -1) {
                    dist[indexNachbar] = dist[indexAktuell] + 1;
                    queue.add(nachbar);
                }
            }
        }

        return dist;
    }

    // ---------------------------------------------------------
    // EXZENTRIZITÄT EINES KNOTENS
    // ---------------------------------------------------------
    public int exzentrizitaet(Knoten k) {
        int[] dist = berechneDistanzen(k);
        int max = 0;
        for (int d : dist) {
            if (d > max) max = d;
        }
        return max;
    }

    // ---------------------------------------------------------
    // EXZENTRIZITÄTEN ALLER KNOTEN
    // ---------------------------------------------------------
    public int[] alleExzentrizitaeten() {
        int n = graph.getKnotenListe().size();
        int[] ecc = new int[n];

        for (int i = 0; i < n; i++) {
            ecc[i] = exzentrizitaet(graph.getKnotenListe().get(i));
        }

        return ecc;
    }

    // ---------------------------------------------------------
    // RADIUS
    // ---------------------------------------------------------
    public int radius(int[] ecc) {
        return Arrays.stream(ecc).min().orElse(-1);
    }

    // ---------------------------------------------------------
    // DURCHMESSER
    // ---------------------------------------------------------
    public int durchmesser(int[] ecc) {
        return Arrays.stream(ecc).max().orElse(-1);
    }

    // ---------------------------------------------------------
    // ZENTRUM
    // ---------------------------------------------------------
    public List<Knoten> zentrum(int[] ecc, int radius) {
        List<Knoten> zentrum = new ArrayList<>();
        for (int i = 0; i < ecc.length; i++) {
            if (ecc[i] == radius) {
                zentrum.add(graph.getKnotenListe().get(i));
            }
        }
        return zentrum;
    }
}
