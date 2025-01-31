package crayon.tasks;

/**
 * Represents a task.
 */
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
     * Marks the task as done by setting {@code isDone} to {@code true}.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting {@code isDone} to {@code false}.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Converts the object into a CSV row representation.
     *
     * @return An array of strings representing the object's data in CSV format.
     */
    public abstract String[] toCsvRow();

    /**
     * Retrieves the type of this object.
     *
     * @return A string representing the object's type.
     */
    public abstract String getType();
}
