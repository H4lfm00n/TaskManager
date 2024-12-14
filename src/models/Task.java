package models;

/**
 * Represents a task with a unique ID, name, due date, and priority.
 */
public class Task {
    private static int idCounter = 1; // Static counter to generate unique IDs for each task.
    private int id;                   // Unique identifier for the task.
    private String name;              // Name or description of the task.
    private String dueDate;           // Due date of the task in YYYY-MM-DD format.
    private int priority;             // Priority level of the task (1 = lowest, 5 = highest).


    public Task(String name, String dueDate, int priority) {
        this.id = idCounter++;       // Assign a unique ID and increment the counter.
        this.name = name;            // Set the task name.
        this.dueDate = dueDate;      // Set the task due date.
        this.priority = priority;    // Set the task priority.
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }


    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                '}';
    }
}
