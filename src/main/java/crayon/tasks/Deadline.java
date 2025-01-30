package crayon.tasks;

import crayon.exceptions.CrayonInvalidFormatException;
import crayon.utils.DateTime;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private final LocalDateTime endDate;

    /**
     * Constructs a Deadline task by parsing the provided description string.
     * The expected format is: <task description> /by <deadline date>.
     *
     * @param description The description string containing the task and deadline.
     */
    private Deadline(String description, LocalDateTime endDate){
        super(description);
        this.endDate = endDate;
    }

    private Deadline(String description, boolean isDone, LocalDateTime endDate){
        super(description, isDone);
        this.endDate = endDate;
    }

    public static Deadline createDeadlineTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("Deadline description cannot be empty\n");
        }

        String[] parts = description.split(" /by ");
        if (parts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /by <endDate>\n");
        }

        String taskDescription = parts[0].trim();
        LocalDateTime endDate = DateTime.stringToDateTime(parts[1].trim(),true);

        return new Deadline(taskDescription, endDate);
    }

    public static Deadline createDeadlineFromCSV(String[] values) throws CrayonInvalidFormatException {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String taskDescription = values[2].trim();
        LocalDateTime endDate = DateTime.parseStoredDateTime(values[4].trim());

        return new Deadline(taskDescription, isDone, endDate);
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String[] toCSVRow() {
        return new String[]{getType(), String.valueOf(isDone), description, "", endDate.toString()};
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.dateTimeToString(endDate) + ")";
    }
}
