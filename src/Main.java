import managers.TaskManager;
import managers.GraphManager;
import models.Task;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskManager taskManager = new TaskManager();
    private static GraphManager graphManager = new GraphManager();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Task Management System ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Undo Last Action");
            System.out.println("6. Redo Last Action");
            System.out.println("7. View Task Dependencies");
            System.out.println("8. Export Task Report");
            System.out.println("9. Exit");
            System.out.println("10. Add Task Dependency");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> editTask();
                case 4 -> deleteTask();
                case 5 -> undoAction();
                case 6 -> redoAction();
                case 7 -> viewDependencies();
                case 8 -> exportReport();
                case 9 -> exit = true;
                case 10 -> addTaskDependency();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        taskManager.addTask(name, dueDate, priority);
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        taskManager.viewTasks();
    }

    private static void editTask() {
        System.out.print("Enter task ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        taskManager.editTask(id, scanner);
    }

    private static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskManager.deleteTask(id)) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void undoAction() {
        if (taskManager.undo()) {
            System.out.println("Last action undone.");
        } else {
            System.out.println("No actions to undo.");
        }
    }

    private static void redoAction() {
        if (taskManager.redo()) {
            System.out.println("Last undone action redone.");
        } else {
            System.out.println("No actions to redo.");
        }
    }

    private static void viewDependencies() {
        System.out.print("Enter task ID to view dependencies: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Task task = taskManager.getTaskById(id);
        if (task == null) {
            System.out.println("Task with ID " + id + " not found.");
            return;
        }

        graphManager.printDependenciesForTask(task);
    }

    private static void addTaskDependency() {
        System.out.print("Enter the ID of the dependent task (Task that needs another task): ");
        int dependentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter the ID of the prerequisite task (Task that must be completed first): ");
        int prerequisiteId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Task dependentTask = taskManager.getTaskById(dependentId);
        Task prerequisiteTask = taskManager.getTaskById(prerequisiteId);

        if (dependentTask == null || prerequisiteTask == null) {
            System.out.println("Invalid task IDs. Please try again.");
            return;
        }

        graphManager.addDependency(dependentTask, prerequisiteTask);
        System.out.println("Added dependency: " + dependentTask.getName() +
                " depends on " + prerequisiteTask.getName());
    }

    private static void exportReport() {
        System.out.print("Enter file name to export report: ");
        String fileName = scanner.nextLine();

        if (taskManager.exportReport(fileName)) {
            System.out.println("Report exported successfully!");
        } else {
            System.out.println("Failed to export report.");
        }
    }
}
