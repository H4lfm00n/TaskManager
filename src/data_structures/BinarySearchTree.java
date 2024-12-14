package data_structures;

/**
 * A generic implementation of a Binary Search Tree (BST).
 * This tree supports insertion and in-order traversal of elements.
 *
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * Represents a single node in the binary search tree.
     */
    private class Node {
        T data;       // The data stored in this node.
        Node left;    // Reference to the left child.
        Node right;   // Reference to the right child.


        Node(T data) {
            this.data = data;
        }
    }

    private Node root; // The root node of the binary search tree.

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, T data) {
        // Base case: If the subtree is empty, create a new node.
        if (root == null) {
            return new Node(data);
        }
        // If the data is less than the current node's data, insert into the left subtree.
        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        }
        // If the data is greater than the current node's data, insert into the right subtree.
        else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }
        // Return the root of the subtree (unchanged for this recursion step).
        return root;
    }


    public void inOrderTraversal() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        // If the subtree is not empty, traverse the left subtree, print the root, and traverse the right subtree.
        if (root != null) {
            inOrderRec(root.left);       // Visit the left child.
            System.out.println(root.data); // Visit the current node.
            inOrderRec(root.right);      // Visit the right child.
        }
    }
}
