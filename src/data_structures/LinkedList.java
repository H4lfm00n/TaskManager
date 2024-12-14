package data_structures;

/**
 * A generic implementation of a singly linked list.
 * This list supports adding, removing, and retrieving elements.
 *
 */
public class LinkedList<T> {

    private class Node {
        T data;       // The data stored in the node.
        Node next;    // Reference to the next node in the list.

        Node(T data) {
            this.data = data;
        }
    }

    private Node head; // Reference to the first node in the list.
    private int size = 0; // Tracks the number of elements in the list.

    public void add(T data) {
        Node newNode = new Node(data); // Create a new node with the given data.

        // If the list is empty, make the new node the head.
        if (head == null) {
            head = newNode;
        } else {
            // Traverse to the end of the list and add the new node.
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode; // Link the new node to the end of the list.
        }
        size++; // Increment the size of the list.
    }

    public boolean remove(T data) {
        // If the list is empty, return false.
        if (head == null) return false;

        // If the head contains the data, remove the head.
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        // Traverse the list to find the node containing the data.
        Node current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        // If the next node contains the data, remove it.
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }

        return false; // Data not found in the list.
    }

    public T get(int index) {
        // Check if the index is valid.
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        // Traverse the list to the specified index.
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data; // Return the data at the specified index.
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        Node current = head; // Start from the head of the list.
        while (current != null) {
            System.out.print(current.data + " -> "); // Print the data in each node.
            current = current.next; // Move to the next node.
        }
        System.out.println("null"); // Indicate the end of the list.
    }
}
