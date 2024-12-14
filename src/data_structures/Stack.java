package data_structures;

import java.util.EmptyStackException;

/**
 * A generic implementation of a Stack using a LinkedList.
 * The stack operates on the LIFO principle (Last In, First Out).
 *
 *
 */
public class Stack<T> {
    // Internal LinkedList to store the stack elements.
    private LinkedList<T> list = new LinkedList<>();

    public void push(T element) {
        list.add(element); // Add the element to the end of the linked list (top of the stack).
    }


    public T pop() {
        // If the stack is empty, throw an exception.
        if (list.size() == 0) throw new EmptyStackException();

        // Get the element at the top of the stack (last element of the list).
        T data = list.get(list.size() - 1);

        // Remove the top element from the stack.
        list.remove(data);

        // Return the removed element.
        return data;
    }


    public T peek() {
        // If the stack is empty, throw an exception.
        if (list.size() == 0) throw new EmptyStackException();

        // Return the top element (last element of the list).
        return list.get(list.size() - 1);
    }


    public boolean isEmpty() {
        // Return true if the list has no elements.
        return list.size() == 0;
    }
}
