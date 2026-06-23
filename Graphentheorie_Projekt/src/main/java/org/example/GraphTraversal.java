package org.example;

import java.util.*;

public class GraphTraversal {

    private final Graph graph;

    public GraphTraversal(Graph graph) {
        this.graph = graph;
    }

    // ---------------------------------------------------------
    // BFS – Breitensuche
    // ---------------------------------------------------------
    public void bfs(Knoten start) {

        System.out.println("\n===== Breitensuche (BFS) ab " + start.getName() + " =====");

        Set<Knoten> besucht = new HashSet<>();
        Queue<Knoten> queue = new LinkedList<>();

        besucht.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {

            Knoten aktueller = queue.poll();
            System.out.print(aktueller.getName() + " ");

            for (Kanten k : aktueller.getNachbarn()) {
                Knoten nachbar = k.getZiel();

                if (!besucht.contains(nachbar)) {
                    besucht.add(nachbar);
                    queue.add(nachbar);
                }
            }
        }

        System.out.println();
    }

    // ---------------------------------------------------------
    // DFS – Tiefensuche (rekursiv)
    // ---------------------------------------------------------
    public void dfs(Knoten start) {

        System.out.println("\n===== Tiefensuche (DFS) ab " + start.getName() + " =====");

        Set<Knoten> besucht = new HashSet<>();
        dfsRekursiv(start, besucht);

        System.out.println();
    }

    private void dfsRekursiv(Knoten knoten, Set<Knoten> besucht) {

        besucht.add(knoten);
        System.out.print(knoten.getName() + " ");

        for (Kanten k : knoten.getNachbarn()) {
            Knoten nachbar = k.getZiel();

            if (!besucht.contains(nachbar)) {
                dfsRekursiv(nachbar, besucht);
            }
        }
    }
}
