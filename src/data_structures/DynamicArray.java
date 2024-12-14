package data_structures;

import java.util.Arrays;

/**
 * A generic implementation of a dynamic array.
 * This array automatically resizes when it reaches its capacity.
 */
public class DynamicArray<T> {
    private Object[] array; // Internal array for storing elements.
    private int size = 0;   // Current number of elements in the array.
    private int capacity;   // Current capacity of the array.


    public DynamicArray(int initialCapacity) {
        this.capacity = initialCapacity;
        array = new Object[initialCapacity]; // Initialize the internal array.
    }


    public void add(T element) {
        // Check if the array is full, and resize it if necessary.
        if (size == capacity) resize();
        array[size++] = element; // Add the element and increment the size.
    }
    private void resize() {
        capacity *= 2; // Double the capacity.
        array = Arrays.copyOf(array, capacity); // Copy elements to a new larger array.
    }

    public T get(int index) {
        // Check if the index is valid.
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) array[index]; // Return the element at the specified index.
    }

    public int size() {
        return size;
    }
}
