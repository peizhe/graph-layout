package com.github.davidmoten.graph;

import java.awt.Dimension;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Main {

    public static void main(String[] args) {
        // Graph<String, Number> g = TestGraphs.getOneComponentGraph();
        Graph<String, String> g = createTestGraph();

        System.out.println(g);
        FRLayout<String, String> layout = new FRLayout<String, String>(g);
        layout.initialize();
        layout.setSize(new Dimension(600, 600));

        displayLayout(layout);
        while (!layout.done()) {
            layout.step();
        }
        displayLayout(layout);
        System.out.println(g);

    }

    public static Graph<String, String> createTestGraph() {
        Graph<String, String> g = new SparseMultigraph<String, String>();
        g.addVertex("Created");
        g.addVertex("Inside");
        g.addVertex("Entered");
        g.addVertex("Never Outside");
        g.addVertex("Outside");
        g.addEdge("In1", "Created", "Never Outside", EdgeType.DIRECTED);
        g.addEdge("In2", "Entered", "Inside", EdgeType.DIRECTED);
        g.addEdge("In3", "Inside", "Inside", EdgeType.DIRECTED);
        g.addEdge("In4", "Never Outside", "Never Outside", EdgeType.DIRECTED);
        g.addEdge("In5", "Outside", "Entered", EdgeType.DIRECTED);
        g.addEdge("Out1", "Created", "Outside", EdgeType.DIRECTED);
        g.addEdge("Out2", "Entered", "Outside", EdgeType.DIRECTED);
        g.addEdge("Out3", "In", "Outside", EdgeType.DIRECTED);
        g.addEdge("Out4", "Never Outside", "Outside", EdgeType.DIRECTED);
        g.addEdge("Out5", "Outside", "Outside", EdgeType.DIRECTED);
        return g;
    }

    private static void displayLayout(FRLayout<String, String> layout) {
        System.out.println("------------ Layout ---------------");
        for (String vertex : layout.getGraph().getVertices()) {
            System.out.println(vertex + ": " + layout.transform(vertex));
        }
    }
}
