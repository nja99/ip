package crayon.tasks;

import crayon.exceptions.CrayonInvalidFormatException;

public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static ToDo createToDoTask(String description) throws CrayonInvalidFormatException {
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("ToDo description cannot be empty");
        }
        return new ToDo(description);
    }

    public static ToDo createToDoFromCsv(String[] values) {
        boolean isDone = Boolean.parseBoolean(values[1].trim());
        String taskDescription = values[2].trim();
        return new ToDo(taskDescription, isDone);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String[] toCsvRow() {
        return new String[]{getType(), String.valueOf(isDone), description, "", ""};
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
