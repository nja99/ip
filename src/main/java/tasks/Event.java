package tasks;

import exceptions.CrayonInvalidFormatException;

public class Event extends Task{

    private final String startDate;
    private final String endDate;

    private Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Event(String description, boolean isDone, String startDate, String endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Event createEventTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("Event description cannot be empty");
        }

        String[] parts = description.split(" /from ");
        if (parts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /from <start datetime> /to <end datetime>");
        }

        String taskDescription = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new CrayonInvalidFormatException("Use: <task> /from <start datetime> /to <end datetime>");
        }
        return new Event(taskDescription, timeParts[0].trim(), timeParts[1].trim());
    }

    public static Event createEventFromCSV(String[] values) {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String description = values[2].trim();
        String startDate = values[3].trim();
        String endDate = values[4].trim();

        return new Event(description, isDone, startDate, endDate);
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String[] toCSVRow() {
        return new String[]{getType(), String.valueOf(isDone), description, startDate, endDate};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
