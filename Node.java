package Graphs;

import java.util.ArrayList;

/**
 * This class represents a Node in a graph. A Node contains the data stored
 * in the graph. It  can also be called Vertex
 */
public class Node {
    private final char name; // char because a letter would represent a Node; final because it shouldn't chane
    private final ArrayList<Edge> edges; // stores all the edges that are connected to this node
    // the edges can be used to find the neighbours
    private boolean visited; // if this Node has been visited during traversals
    private Node previous; // previous Node that led to "this" Node

    /**
     * Constructor to initialize the instance variable
     *
     * @param name the Name of this Node, can change to String
     */
    public Node(char name) {
        this.name = name;
        edges = new ArrayList<>();
    }

    /**
     * This method connects an edge to this node
     * the edge is connected by making the start point of the Edge be "this" node
     *
     * @param edge the edge to add
     */
    public void addEdge(Edge edge) {
        edge.setStartNode(this);
        edges.add(edge);
    }

    /**
     * getter method for edges
     *
     * @return ArrayList<Edge> containing all the edges that are connected to this Node
     */
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Method to check if "this" Node has neighbours. The loop will end and return true if
     * a neighbour is found
     *
     * @return True if "this" Node has neighbours and false if not
     */
    public boolean hasEdges() {
        boolean has = false;
        for (int i = 0; i < edges.size() && !has; i++) {
            if (edges.get(i) != null) {
                has = true;
            }
        }
        return has;
    }

    /**
     * getter method for visted
     *
     * @return returns visited
     */
    public boolean visited() {
        return visited;
    }

    /**
     * Setter method for visited
     *
     * @param flag new visited state
     */
    public void setVisited(boolean flag) {
        this.visited = flag;
    }

    /**
     * @return A string representation of the class -> The name of the Node
     */
    @Override
    public String toString() {
        return "" + name;
    }

}
