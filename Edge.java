package Graphs;

/**
 * This class represents an Edge in a Graph. An edge is the line that connects two
 * Nodes/vertices
 */
public class Edge {
    private int weight; // weight of this edge
    private Node startNode; // starting Node of this edge
    private Node endNode; // ending Node of this edge

    /**
     * Constructor
     *
     * @param weight  the weight of this edge
     * @param endNode the end Node point of this Edge. The start point is "this"
     */
    public Edge(int weight, Node endNode) {
        this.weight = weight;
        this.endNode = endNode;
    }

    /**
     * setter method for startNode instance variable
     *
     * @param startNode
     */
    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    /**
     * getter method for endNode instance variable
     *
     * @return endNode
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * getter method for weight instance variable
     *
     * @return weight
     */
    public int getWeight() {
        return weight;
    }
}
