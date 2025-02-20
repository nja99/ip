package crayon.storage;

import crayon.tasks.Task;

/**
 * This class represents a stub for a task.
 */
public class TaskStub extends Task {

    private final String type;
    private final String[] csvRow;

    /**
     * Constructs a new TaskStub instance.
     *
     * @param type The type of the task.
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     * @param csvRow The CSV row of the task.
     */
    public TaskStub(String type, String description, boolean isDone, String[] csvRow) {
        super(description, isDone);
        this.type = type;
        this.csvRow = csvRow;
    }

    /**
     * Returns the CSV row of the task.
     *
     * @return The CSV row of the task.
     */
    @Override
    public String[] toCsvRow() {
        return csvRow;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return type;
    }
}
