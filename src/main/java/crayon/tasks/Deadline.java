package crayon.tasks;

import java.time.LocalDateTime;

import crayon.exceptions.CrayonInvalidFormatException;
import crayon.utils.DateTime;

/**
 * Represents a Deadline task in Crayon.
 */
public class Deadline extends Task {

    private final LocalDateTime endDate;

    private Deadline(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    private Deadline(String description, boolean isDone, LocalDateTime endDate) {
        super(description, isDone);
        this.endDate = endDate;
    }

    /**
     * Creates a Deadline task from the provided description string.
     * The expected format is: (task) /by (deadline datetime).
     *
     * @param description The description string containing the task and deadline.
     * @return The Deadline task created from the description.
     * @throws CrayonInvalidFormatException If the description is in an invalid format.
     */
    public static Deadline createDeadlineTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("Deadline description cannot be empty\n");
        }

        String[] parts = description.split(" /by ");
        if (parts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /by <endDate>\n");
        }

        String taskDescription = parts[0].trim();
        LocalDateTime endDate = DateTime.stringToDateTime(parts[1].trim(), true);

        return new Deadline(taskDescription, endDate);
    }

    /**
     * Creates a Deadline task from the provided CSV values.
     *
     * @param values The CSV values to create the Deadline task from.
     * @return The Deadline task created from the CSV values.
     * @throws CrayonInvalidFormatException If the CSV values are in an invalid format.
     */
    public static Deadline createDeadlineFromCsv(String[] values) throws CrayonInvalidFormatException {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String taskDescription = values[2].trim();
        LocalDateTime endDate = DateTime.parseStoredDateTime(values[4].trim());

        return new Deadline(taskDescription, isDone, endDate);
    }

    /**
     * Gets the end date of the Deadline task.
     *
     * @return The end date of the Deadline task.
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Gets the end date of the Deadline task.
     *
     * @return The end date of the Deadline task.
     */
    @Override
    public String[] toCsvRow() {
        return new String[]{getType(), String.valueOf(isDone), description, "", endDate.toString()};
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.dateTimeToString(endDate) + ")";
    }
}
