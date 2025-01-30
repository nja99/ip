package tasks;

import exceptions.CrayonInvalidFormatException;

public class Deadline extends Task{

    private final String endDate;

    /**
     * Constructs a tasks.Deadline task by parsing the provided description string.
     * The expected format is: <task description> /by <deadline date>.
     *
     * @param description The description string containing the task and deadline.
     */
    private Deadline(String description, String endDate){
        super(description);
        this.endDate = endDate;
    }

    private Deadline(String description, boolean isDone, String endDate){
        super(description, isDone);
        this.endDate = endDate;
    }

    public static Deadline createDeadlineTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("Deadline description cannot be empty");
        }

        String[] parts = description.split(" /by ");
        if (parts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /by <endDate>");
        }

        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    public static Deadline createDeadlineFromCSV(String[] values) {
        String description = values[1].trim();
        boolean isDone = Boolean.parseBoolean(values[2].trim());
        String endDate = values[4].trim();

        return new Deadline(description, isDone, endDate);
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String[] toCSVRow() {
        return new String[]{getType(), String.valueOf(isDone), description, "", endDate};
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate + ")";
    }
}
