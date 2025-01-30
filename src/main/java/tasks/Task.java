package tasks;

import java.io.IOException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a new task with the specified description
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark task as completed
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmark task completion
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public abstract String[] toCSVRow();
    public abstract String getType();
}
