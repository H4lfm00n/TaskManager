package managers;

import models.Task;
import utils.ReportExporter;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages tasks and provides functionalities such as adding, editing, deleting,
 * viewing tasks, and undo/redo operations. Supports exporting tasks to a file.
 */
public class TaskManager {
    private List<Task> tasks = new ArrayList<>(); // List to store all tasks.
    private Deque<List<Task>> undoStack = new LinkedList<>(); // Stack to track states for undo.
    private Deque<List<Task>> redoStack = new LinkedList<>(); // Stack to track states for redo.


    public void addTask(String name, String dueDate, int priority) {
        saveState(); // Save the current state for undo/redo functionality.
        Task task = new Task(name, dueDate, priority); // Create a new task.
        tasks.add(task); // Add the task to the list.
    }


    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task); // Print each task's details.
            }
        }
    }


    public void editTask(int id, java.util.Scanner scanner) {
        Task task = getTaskById(id); // Retrieve the task by its ID.
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        // Prompt for new values, leaving fields unchanged if the user inputs nothing.
        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) task.setName(name);

        System.out.print("Enter new due date (YYYY-MM-DD, leave blank to keep current): ");
        String dueDate = scanner.nextLine();
        if (!dueDate.isBlank()) task.setDueDate(dueDate);

        System.out.print("Enter new priority (1-5, leave blank to keep current): ");
        String priorityInput = scanner.nextLine();
        if (!priorityInput.isBlank()) task.setPriority(Integer.parseInt(priorityInput));
    }


    public boolean deleteTask(int id) {
        Task task = getTaskById(id); // Retrieve the task by its ID.
        if (task != null) {
            saveState(); // Save the current state for undo/redo functionality.
            tasks.remove(task); // Remove the task from the list.
            return true;
        }
        return false; // Task not found.
    }


    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) { // Check if the task ID matches.
                return task;
            }
        }
        return null; // Return null if no task is found.
    }


    public boolean undo() {
        if (undoStack.isEmpty()) { // Check if there is a state to undo.
            System.out.println("No actions to undo.");
            return false;
        }
        redoStack.push(new ArrayList<>(tasks)); // Save the current state to the redo stack.
        tasks = undoStack.pop(); // Revert to the last saved state.
        return true;
    }


    public boolean redo() {
        if (redoStack.isEmpty()) { // Check if there is a state to redo.
            System.out.println("No actions to redo.");
            return false;
        }
        undoStack.push(new ArrayList<>(tasks)); // Save the current state to the undo stack.
        tasks = redoStack.pop(); // Revert to the last undone state.
        return true;
    }


    public boolean exportReport(String fileName) {
        return ReportExporter.exportTasks(tasks, fileName); // Delegate to the ReportExporter utility.
    }


    private void saveState() {
        undoStack.push(new ArrayList<>(tasks)); // Save a copy of the current state to the undo stack.
        redoStack.clear(); // Clear the redo stack whenever a new state is saved.
    }
}
