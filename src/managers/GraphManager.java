package managers;

import data_structures.Graph;
import models.Task;

import java.util.List;

/**
 * Manages a directed graph of tasks and their dependencies.
 * This class provides high-level operations for adding tasks,
 * establishing dependencies, and detecting circular dependencies.
 */
public class GraphManager {
    // The underlying dependency graph represented as a directed graph.
    private final Graph dependencyGraph;

    public GraphManager() {
        this.dependencyGraph = new Graph();
    }

    public void addTask(Task task) {
        dependencyGraph.addTask(task); // Delegate to the Graph class.
    }

    public void addDependency(Task from, Task to) {
        dependencyGraph.addDependency(from, to); // Add the dependency to the graph.
        System.out.println("Added dependency: " + from.getName() + " -> " + to.getName());
    }


    public void removeDependency(Task from, Task to) {
        dependencyGraph.removeDependency(from, to); // Remove the dependency from the graph.
        System.out.println("Removed dependency: " + from.getName() + " -> " + to.getName());
    }


    public void printDependenciesForTask(Task task) {
        // Retrieve the list of dependencies for the given task.
        List<Task> dependencies = dependencyGraph.getDependencies(task);

        if (dependencies == null || dependencies.isEmpty()) {
            System.out.println("No dependencies for task: " + task.getName());
        } else {
            System.out.println("Dependencies for " + task.getName() + ":");
            for (Task dependency : dependencies) {
                System.out.println(" - " + dependency.getName()); // Print each dependency.
            }
        }
    }

    public boolean hasCircularDependency() {
        return dependencyGraph.hasCycle(); // Check for cycles using the Graph class.
    }
}
