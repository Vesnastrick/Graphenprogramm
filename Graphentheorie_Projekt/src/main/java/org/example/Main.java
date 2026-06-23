package org.example;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph();

        try {
            graph.CSVimport();
            System.out.println("CSV erfolgreich importiert.\n");

        } catch (GraphException e) {
            System.out.println("Fehler: " + e.getMessage());
            return;
        }

        System.out.println("----- Adjazenzmatrix -----");
        graph.ausgabeMatrix();

        System.out.println("\n----- Gesamter Graph -----");
        graph.ausgabeCSV();

        // ----- BFS & DFS -----
        // ----- DISTANZEN, EXZENTRIZITÄTEN, RADIUS, DURCHMESSER, ZENTRUM -----
        DistanceCalculator calc = new DistanceCalculator(graph);

        int[] ecc = calc.alleExzentrizitaeten();
        int radius = calc.radius(ecc);
        int durchmesser = calc.durchmesser(ecc);
        var zentrum = calc.zentrum(ecc, radius);

        System.out.println("\n----- Exzentrizitäten -----");
        for (int i = 0; i < ecc.length; i++) {
            System.out.println(graph.getKnotenListe().get(i).getName() + ": " + ecc[i]);
        }

        System.out.println("\nRadius: " + radius);
        System.out.println("Durchmesser: " + durchmesser);
        System.out.println("Zentrum: " + zentrum);

        GraphTraversal traversal = new GraphTraversal(graph);

        traversal.bfs(graph.getKnotenListe().get(0));
        traversal.dfs(graph.getKnotenListe().get(0));
    }

}
