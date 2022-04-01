package Graphs.QueueClass;

/**
 * Queue class behind a linked list implementation.<br>
 * It uses Generics
 *
 * @param <T> Type of data to store in the Queue
 * @author Ife Sunmola
 */
public class Node <T> {
    private T value;
    private Node<T> next;

    /**
     * Constructor to initialize the instance variables
     *
     * @param value Generic type T: the value to store in the Node
     * @param next  Pointer to the next node from "this" node
     */
    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Getter method for value
     *
     * @return Generic Type T: the value stored in this node
     */
    public T getValue() {
        return value;
    }

    /**
     * Setter method for value
     *
     * @param value new value to update with
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Getter method for next
     *
     * @return Generic node Type T: pointer to the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setter method for value
     *
     * @param next new next to update with
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * @return String -> the value as a String
     */
    @Override
    public String toString() {
        return value + "";
    }
}
