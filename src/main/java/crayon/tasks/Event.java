package crayon.tasks;

import crayon.exceptions.CrayonInvalidDateTimeException;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.utils.DateTime;

import java.time.LocalDateTime;

public class Event extends Task{

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

    public static Event createEventFromCSV(String[] values) throws CrayonInvalidDateTimeException {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String taskDescription = values[2].trim();
        LocalDateTime startDate = DateTime.parseStoredDateTime(values[3].trim());
        LocalDateTime endDate = DateTime.parseStoredDateTime(values[4].trim());

        return new Event(taskDescription, isDone, startDate, endDate);
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String[] toCSVRow() {
        return new String[]{getType(), String.valueOf(isDone), description, startDate.toString(), endDate.toString()};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTime.dateTimeToString(startDate) + " to: " + DateTime.dateTimeToString(endDate) + ")";
    }
}
