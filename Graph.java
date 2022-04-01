package Graphs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This  class represents a Graph. The toString gotten from the Graph should be read as:
 * A --> B(50) C(60):
 * ........ A is connected to B with weight of 50; A is connected to C with weight of 60
 * --------------------------------------------------
 * B ---> A (50) D (120) E (90):
 * ........ B is connected to A with weight of 50; B is connected to D with weight of 120
 * ........ B is connected to E with weight of 90.
 * etc
 */
public class Graph {
    private final ArrayList<Node> nodes; // list of all the nodes in this graph

    public Graph(String fileName) {
        nodes = new ArrayList<>();
        for (int i = 65; i < 91; i++) { // add A to Z as Nodes
            nodes.add(new Node((char) i));
        }
        try {
            createGraphFromAdjMatrix(fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void createGraphFromAdjMatrix(String fileName) throws IOException {// create a graph from an adjacency matrix
        Scanner sc = new Scanner(new File(fileName));
        String line;
        int currentRow = 0;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] rowWeights = line.split(", ");
            Node startVertex = nodes.get(currentRow);

            for (int i = 0; i < rowWeights.length; i++) {
                int weight = Integer.parseInt(rowWeights[i]);
                if (weight != 0) {
                    Node endNode = nodes.get(i);
                    startVertex.addEdge(new Edge(weight, endNode));
                }
            }
            currentRow++;
        }
        sc.close();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node curr_node : nodes) { // for each node:
            if (curr_node.hasEdges()) {// if the curr_node has edges
                ArrayList<Edge> nodeNeighbours = curr_node.getEdges();// store the edges so they can be processed
                result.append(curr_node).append(" ---> ");// neighbours of curr_node will be added below

                // the start Node of each edge below is curr_node.
                // the end Node of each edge below is a neighbour of curr_node
                for (Edge edge : nodeNeighbours) { // for each edge that is connected to curr_node
                    result.append(edge.getEndNode());// add the neighbour
                    // and the weight:
                    result.append(" (" + edge.getWeight() + ") ");
                }
                result.append("\n");
            }
        }
        return result.toString();
    }
}
