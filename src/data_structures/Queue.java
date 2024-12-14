package data_structures;

/**
 * A generic implementation of a Queue using a LinkedList.
 * The queue operates on the FIFO principle (First In, First Out).
 *
 */
public class Queue<T> {
    // Internal LinkedList to store the queue elements.
    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T element) {
        list.add(element); // Add the element to the end of the linked list.
    }

    public T dequeue() {
        // Check if the queue is empty.
        if (list.isEmpty()) return null;

        // Get the first element in the queue.
        T data = list.get(0);

        // Remove the first element from the queue.
        list.remove(data);

        // Return the dequeued element.
        return data;
    }

    public boolean isEmpty() {
        return list.isEmpty(); // Use LinkedList's isEmpty() method.
    }

    public int size() {
        return list.size(); // Use LinkedList's size() method.
    }
}
