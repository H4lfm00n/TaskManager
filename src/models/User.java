package models;

import data_structures.LinkedList;

/**
 * Represents a user who can manage a list of tasks.
 */
public class User {
    private String id;                  // Unique identifier for the user.
    private String name;                // Name of the user.
    private LinkedList<Task> taskList;  // A list of tasks associated with the user.

    public User(String id, String name) {
        this.id = id;                   // Set the user's unique ID.
        this.name = name;               // Set the user's name.
        this.taskList = new LinkedList<>(); // Initialize an empty linked list of tasks.
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public LinkedList<Task> getTaskList() {
        return taskList;
    }
}
