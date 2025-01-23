package tasks;

public class Task {
    protected String description;
    private boolean isDone;

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
}
