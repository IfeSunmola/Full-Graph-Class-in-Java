package Graphs.QueueAndStack;

/**
 * StackAndQueue.Queue class implemented using a Generic Linked List
 */
public class Queue<T> {

    private Node<T> first; // pointer the first
    private Node<T> last; // and last StackAndQueue.Node in the linked list

    public Queue() {
        first = last = null;
    }

    /**
     * The StackAndQueue.Queue is empty if the first StackAndQueue.Node object is null
     *
     * @return boolean -> True if the StackAndQueue.Queue (linked list) is empty and false if it's not.
     */
    public boolean isEmpty() {

        return first == null;
    }

    /**
     * This method adds the newValue at the end of the StackAndQueue.Queue
     *
     * @param newValue the generic newValue to add to the StackAndQueue.Queue
     */
    public void enqueue(T newValue) {

        Node<T> newNode = new Node<>(newValue, null);
        if (isEmpty()) { // empty linked list, make newValue will be first and last
            first = last = newNode;
        }
        else {
            // not empty, add to the end of the linked list by making the
            // last StackAndQueue.Node's next be the newNode (it was null before)
            // then fix the connection by making last the newNode
            last.setNext(newNode);
            last = newNode;
        }
    }

    /**
     * This method removes the first StackAndQueue.Node in the queue and returns its value
     *
     * @return Generic Type T -> if there was a StackAndQueue.Node to dequeue <br> OR null if there was None
     */
    public T dequeue() {
        T dequeued = null;
        if (!isEmpty()) {
            dequeued = first.getValue();
            first = first.getNext();
        }
        return dequeued;
    }

    /**
     * This method returns the data in front of the StackAndQueue.Queue without deleting it
     *
     * @return Generic Type T -> if there was a StackAndQueue.Node in front <br> OR null if there was None
     */
    public T front() {

        T front = null;
        if (!isEmpty()) {
            front = first.getValue();
        }
        return front;
    }


    /**
     * toString method
     *
     * @return String -> a string containing the data in the queue.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Front    ----->    Back\n");
        if (isEmpty()) {
            result.append("StackAndQueue.Queue is Empty");
        }
        else {
            Node<T> curr = first; // don't lose reference
            while (curr != null) { // while not at the end of the queue
                result.append(curr); // add current value
                result.append("    "); // separator
                curr = curr.getNext(); // move to next StackAndQueue.Node
            }
        }
        return result.toString();
    }
}
