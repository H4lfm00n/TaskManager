package data_structures;

import models.Task;

import java.util.*;

/**
 * Represents a directed graph for managing task dependencies.
 * Each task is a node, and a directed edge from one task to another
 * indicates a dependency (e.g., Task A depends on Task B).
 */
public class Graph {
    // Adjacency list representation of the graph
    private final Map<Task, List<Task>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }


    public void addTask(Task task) {
        // Ensures the task exists in the adjacency list with an empty dependency list.
        adjacencyList.putIfAbsent(task, new ArrayList<>());
    }

    public void addDependency(Task from, Task to) {
        // Add 'to' as a dependency for 'from'.
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public void removeDependency(Task from, Task to) {
        // Retrieve the dependency list for 'from' and remove 'to'.
        List<Task> dependencies = adjacencyList.get(from);
        if (dependencies != null) {
            dependencies.remove(to);
        }
    }

    public List<Task> getDependencies(Task task) {
        // Return the list of dependencies or an empty list if the task has none.
        return adjacencyList.getOrDefault(task, new ArrayList<>());
    }

    public boolean hasCycle() {
        Set<Task> visited = new HashSet<>();  // Tracks visited nodes.
        Set<Task> recStack = new HashSet<>(); // Tracks nodes in the current recursion stack.

        // Check for cycles in the graph, starting from each node.
        for (Task task : adjacencyList.keySet()) {
            if (hasCycleDFS(task, visited, recStack)) {
                return true; // A cycle is detected.
            }
        }
        return false; // No cycles detected.
    }


    private boolean hasCycleDFS(Task current, Set<Task> visited, Set<Task> recStack) {
        // If the current node is already in the recursion stack, a cycle exists.
        if (recStack.contains(current)) {
            return true;
        }
        // If the current node has already been processed, no cycle exists.
        if (visited.contains(current)) {
            return false;
        }

        // Mark the current node as visited and add it to the recursion stack.
        visited.add(current);
        recStack.add(current);

        // Check all dependencies (edges) of the current node.
        List<Task> dependencies = adjacencyList.get(current);
        if (dependencies != null) {
            for (Task dependency : dependencies) {
                if (hasCycleDFS(dependency, visited, recStack)) {
                    return true; // A cycle is detected in the dependency chain.
                }
            }
        }

        // Remove the current node from the recursion stack before returning.
        recStack.remove(current);
        return false;
    }
}
