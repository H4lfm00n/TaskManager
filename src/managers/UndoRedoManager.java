package managers;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Manages undo and redo functionality for actions performed in the Task Management System.
 */
public class UndoRedoManager<T> {
    private final Deque<T> undoStack; // Stack to store undo states
    private final Deque<T> redoStack; // Stack to store redo states

    public UndoRedoManager() {
        this.undoStack = new LinkedList<>();
        this.redoStack = new LinkedList<>();
    }


    public void saveState(T state) {
        undoStack.push(state);
        redoStack.clear(); // Clear redo stack on new action
    }


    public T undo() {
        if (undoStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return null;
        }
        T state = undoStack.pop();
        redoStack.push(state);
        return state;
    }


    public T redo() {
        if (redoStack.isEmpty()) {
            System.out.println("No actions to redo.");
            return null;
        }
        T state = redoStack.pop();
        undoStack.push(state);
        return state;
    }


    public boolean canUndo() {
        return !undoStack.isEmpty();
    }


    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}
