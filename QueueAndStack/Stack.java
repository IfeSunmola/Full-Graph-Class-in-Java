package Graphs.QueueAndStack;

/**
 * Generic stack class implemented using arrays
 * The stack is implemented in reverse order. i.e in the actual array,
 * push and pop aren't happening on the front of the array. They're happening at the back
 *
 * @author Ife Sunmola
 */
public class Stack <T> {
    private T[] stackList;
    private int size;

    /**
     * Constructor to initialize the instance variables
     *
     * @param maxSize the maximum amount of data the stack can hold
     */
    public Stack(int maxSize) {
        stackList = (T[]) new Object[maxSize]; // not using T as the direct type because of erasure
        size = 0;
    }

    /**
     * @return true if the stack is empty and false if it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * private method to resize the stackList when it gets full.
     * WARNING: CAN GET VERY EXPENSIVE!!
     */
    private void resize() {
        T[] newStackList = (T[]) new Object[stackList.length];
        System.arraycopy(stackList, 0, newStackList, 0, stackList.length);
        stackList = newStackList;
    }

    /**
     * method to add to the stack array
     *
     * @param newValue the value to add
     */
    public void push(T newValue) {
        if (size >= stackList.length) {
            resize();
        }
        stackList[size++] = newValue;
    }

    /**
     * method to delete and return the data on top of the stack
     *
     * @return Generic T if there was a data to pop OR Null if there was no data
     */
    public T pop() {
        T popped = null;
        if (!isEmpty()) {
            size--;
            popped = stackList[size];
        }
        return popped;
    }

    /**
     * method to get the data on top of the , without deleting
     *
     * @return Generic T if there was a data on top OR Null if there was no data
     */
    public T top() {
        T top = null;
        if (!isEmpty()) {
            top = stackList[size];
        }
        return top;
    }

    /**
     * to string method to return a text representation of the StackAndQueue.Stack
     * The loop is going in reverse becausse the stack is in reverse (see comment at the beginning
     * of the file)
     *
     * @return String -> contains the stack data, from top - bottom
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Front    ----->    Back\n");

        if (isEmpty()) {
            result.append("StackAndQueue.Stack is Empty");
        }
        else {
            for (int i = size - 1; i >= 0; i--) {
                result.append(stackList[i]);
                result.append("    ");
            }
        }
        return result.toString();
    }
}
