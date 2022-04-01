package Graphs;

import Graphs.QueueAndStack.Queue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This  class represents a Graph. The toString gotten from the Graph should be read as:
 * <p> A --> B(50) C(60):
 * ........ A is connected to B with weight of 50; A is connected to C with weight of 60</p>
 *
 * <p> B ---> A (50) D (120) E (90):
 * ........ B is connected to A with weight of 50; B is connected to D with weight of 120
 * ........ B is connected to E with weight of 90.</p>
 * etc
 *
 *
 * <p>The.txt file containing the adjacency matrix (see the .txt files and .png files):
 * ..... Should not have the name of the node. The createGraphFromAdjMatrix() method assumes each line corresponds
 * ........... to a Node. i.e, line 1 corresponds to Node A. line 2 corresponds to Node B, etc
 * ..... Be separated by ", "
 * ..... Should have the same amount of rows and columns (excluding commas). If two nodes are not connected, leaving it
 * ........... blank would cause problems. Use 0 instead
 *
 * </p>
 */
public class Graph {
    private final ArrayList<Node> nodes; // list of all the nodes in this graph
    private int actualSize; // actual amount of nodes in the graph. The ArrayList above will have letters A - Z
    // but not all of them will be used

    /**
     * <p> Constructor to fill the nodes ArrayList with letters A to Z or fill with the node names you want in your graph
     * This constructor also creates the graph from the .txt file passed as a parameter. The file should contain
     * a graph in adjacent matrix format (see the comment at the beginning  of this class.</p>
     *
     * @param fileName location of the file
     */
    public Graph(String fileName) {
        nodes = new ArrayList<>();
        actualSize = 0;
        for (int i = 65; i < 91; i++) { // add A to Z as Nodes
            nodes.add(new Node((char) i));
        }
        try {
            createGraphFromAdjMatrix(fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * This helper method creates a graph from the adjacency matrix file that was passed
     * In the file, a line contains the weights of all the Nodes connected to the current Node. i.e, this line:
     *
     * @param fileName location of the file containing the matrix file
     * @throws IOException throws IOException if the file could not be opened
     */
    private void createGraphFromAdjMatrix(String fileName) throws IOException {// create a graph from an adjacency matrix
        Scanner sc = new Scanner(new File(fileName));
        String line;
        int currentRow = 0;// represents Node A, ,B, C, etc
        while (sc.hasNextLine()) { // while there's still a line to read
            line = sc.nextLine(); //store the line for processing

            //recall: each line contains the weights of the edges
            String[] rowWeights = line.split(", ");
            // get the corresponding node name, i.e letter. E.g. currentRow of 1 returns Node A. 10 returns Node J
            Node startNode = nodes.get(currentRow);

            // for each rowWeight:
            for (int i = 0; i < rowWeights.length; i++) {
                int weight = Integer.parseInt(rowWeights[i]);// convert the weight to int
                if (weight != 0) {// if the weight is not 0, there's a connection,
                    Node endNode = nodes.get(i); // get the node
                    startNode.addEdge(new Edge(weight, endNode)); // and make it an edge of startNode

                }
            }
            currentRow++; // repeat
        }
        actualSize = currentRow;// the amount of rows in the .txt file is the amount of Nodes that was added
        sc.close();
    }

    /**
     * toString method to print the graph. See the comment at the beginning of this class
     *
     * @return String
     */
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
                    // and the weight in brackets:
                    result.append(" (").append(edge.getWeight()).append(") ");
                }
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * public method to do a BFS traversal recursively and print the order in which the nodes are visited. A helper
     * method is used to achieve this
     *
     * @param startIndex the index of the Node to start the traversal from.
     * @return a String containing the order in which the nodes were visited
     */
    public String breadthFirstTraversal(int startIndex) {
        Queue<Node> q = new Queue<>();
        Node start = nodes.get(startIndex);
        start.setVisited(true);
        q.enqueue(start);// add it to the queue to begin recursion
        StringBuilder result = new StringBuilder("Breadth-first traversal starting from " + start + ":\n");
        return bfsRec(q, result);
    }

    /**
     * Recursive helper method to do a breadth first traversal of the Graph
     *
     * @param q      the queue holding the adjacent nodes of "curr"
     * @param result the result string
     * @return String containing the order in which the nodes where visited
     */
    private String bfsRec(Queue<Node> q, StringBuilder result) {
        if (q.isEmpty()) { // base case, empty queue, all nodes have been visited, return current result
            return result.toString();
        }

        Node curr = q.dequeue();
        result.append(curr).append(" ");

        ArrayList<Edge> adjacents = curr.getEdges(); // get all adjacent edges
        for (Edge adjacentEdge : adjacents) {// for each adjacent edge
            // recall that the endNode of an edge is a node that is connected to curr
            // getEndNode returns a node that is connected to curr
            if (!adjacentEdge.getEndNode().visited()) { // if it was not visited
                q.enqueue(adjacentEdge.getEndNode());// enqueue it so it can be added to the string on the next recursive call
                adjacentEdge.getEndNode().setVisited(true); // it has been visited
            }
        }
        result = new StringBuilder(bfsRec(q, result)); // repeat
        return result.toString();
    }

    /**
     * Only using this in main to generate a random number to start the BFS at
     **/
    public int size() {
        return actualSize;
    }

    /**
     * public method to do a Depth first search traversal recursively and print the order in which the nodes are visited. A helper
     * method is used to achieve this
     *
     * @param startIndex the index of the Node to start the traversal from.
     * @return a String containing the order in which the nodes were visited
     */
    public String depthFirstTraversal(int startIndex) {
        Node start = nodes.get(startIndex);
        StringBuilder result = new StringBuilder("Depeth-first traversal starting from " + start + ":\n");
        result = new StringBuilder(dfsRec(start, result));
        return result.toString();
    }

    /**
     * Recursive helper method to do a Depth first traversal of the Graph, using the runtime stack
     *
     * @param curr   the node the depth first search from
     * @param result the result string
     * @return String containing the order in which the nodes where visited
     */
    private String dfsRec(Node curr, StringBuilder result) {
        if (!curr.visited()) {// current node has not been visited
            curr.setVisited(true);//visit it
            result.append(curr).append(" ");// add the value to the string
            ArrayList<Edge> adjEdges = curr.getEdges();// get its adjacent edges
            for (Edge edge : adjEdges) {// and for each adjacent edge
                result = new StringBuilder(dfsRec(edge.getEndNode(), result));// repeat the method
            }
        }
        return result.toString();
    }

    /**
     * method to set the visited instance of all the nodes in this graph to false, so a new traversal can
     * be done
     */
    public void reset() {
        for (Node node : nodes) {
            node.setVisited(false);
        }
    }
}
