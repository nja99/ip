package crayon.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a new task with the specified description
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public abstract String[] toCsvRow();
    public abstract String getType();
}
