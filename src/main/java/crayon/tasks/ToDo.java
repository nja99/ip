package crayon.tasks;

import crayon.exceptions.CrayonInvalidFormatException;

/**
 * Represents a ToDo task.
 * A ToDo task has a description.
 */
public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a ToDo task from the provided description string.
     *
     * @param description The description string containing the task.
     * @return The ToDo task created from the description.
     * @throws CrayonInvalidFormatException If the description is empty.
     */
    public static ToDo createToDoTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("ToDo description cannot be empty");
        }
        return new ToDo(description);
    }

    /**
     * Creates a ToDo task from the provided CSV values.
     *
     * @param values The CSV values to create the ToDo task from.
     * @return The ToDo task created from the CSV values.
     */
    public static ToDo createToDoFromCsv(String[] values) {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String taskDescription = values[2].trim();
        return new ToDo(taskDescription, isDone);
    }

    /**
     * Retrieves the type of this object.
     *
     * @return A string representing the object's type.
     */
    @Override
    public String getType() {
        return "todo";
    }

    /**
     * Converts the object into a CSV row representation.
     *
     * @return An array of strings representing the object's data in CSV format.
     */
    @Override
    public String[] toCsvRow() {
        return new String[]{getType(), String.valueOf(isDone), description, "", ""};
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return The string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
