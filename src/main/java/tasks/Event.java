package tasks;

import exceptions.CrayonInvalidFormatException;

public class Event extends Task{

    private final String startTime;
    private final String endTime;

    private Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
