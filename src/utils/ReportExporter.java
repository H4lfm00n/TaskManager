package utils;

import models.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExporter {


    public static boolean exportTasks(List<Task> tasks, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Task Report");
            writer.newLine();
            writer.write("=============");
            writer.newLine();

            if (tasks.isEmpty()) {
                writer.write("No tasks available.");
            } else {
                for (Task task : tasks) {
                    writer.write(formatTask(task));
                    writer.newLine();
                }
            }

            System.out.println("Report exported successfully to " + fileName);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to export report: " + e.getMessage());
            return false;
        }
    }

    private static String formatTask(Task task) {
        return String.format(
                "ID: %d, Name: %s, Due Date: %s, Priority: %d",
                task.getId(),
                task.getName(),
                task.getDueDate(),
                task.getPriority()
        );
    }
}
