package crayon.tasks;

import java.time.LocalDateTime;

import crayon.exceptions.CrayonInvalidDateTimeException;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.utils.DateTime;

/**
 * Represents an Event task in Crayon.
 */
public class Event extends Task {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Event(String description, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates an Event task from the provided description string.
     * The expected format is: (task) /from (start datetim) /to (end datetime).
     *
     * @param description The description string containing the task and event timings.
     * @return The Event task created from the description.
     * @throws CrayonInvalidFormatException If the description is in an invalid format.
     */
    public static Event createEventTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("Event description cannot be empty\n");
        }

        String[] parts = description.split(" /from ");
        if (parts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /from <start datetime> /to <end datetime>\n");
        }

        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /from <start datetime> /to <end datetime>\n");
        }

        String taskDescription = parts[0].trim();
        LocalDateTime startDate = DateTime.stringToDateTime(timeParts[0].trim(), false);
        LocalDateTime endDate = DateTime.stringToDateTime(timeParts[1].trim(), true);

        return new Event(taskDescription, startDate, endDate);
    }

    /**
     * Creates an Event task from the provided CSV values.
     *
     * @param values The CSV values to create the Event task from.
     * @return The Event task created from the CSV values.
     * @throws CrayonInvalidDateTimeException If the date and time format is invalid.
     */
    public static Event createEventFromCsv(String[] values) throws CrayonInvalidDateTimeException {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String taskDescription = values[2].trim();
        LocalDateTime startDate = DateTime.parseStoredDateTime(values[3].trim());
        LocalDateTime endDate = DateTime.parseStoredDateTime(values[4].trim());

        return new Event(taskDescription, isDone, startDate, endDate);
    }

    /**
     * Gets the start date of the Event task.
     *
     * @return The start date of the Event task.
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Converts the Event task to a CSV row.
     *
     * @return The CSV row of the Event task.
     */
    @Override
    public String[] toCsvRow() {
        return new String[]{getType(), String.valueOf(isDone), description, startDate.toString(), endDate.toString()};
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTime.dateTimeToString(startDate)
                + " to: " + DateTime.dateTimeToString(endDate) + ")";
    }
}
