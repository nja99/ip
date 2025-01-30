package tasks;

import exceptions.CrayonInvalidFormatException;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description);
    }

    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static ToDo createToDoTask(String description) throws CrayonInvalidFormatException{
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("ToDo description cannot be empty");
        }
        return new ToDo(description);
    }

    public static ToDo createToDoFromCSV(String[] values) {
        String description = values[1].trim();
        boolean isDone = Boolean.parseBoolean(values[2].trim());
        return new ToDo(description, isDone);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String[] toCSVRow() {
            return new String[]{getType(), String.valueOf(isDone), description, "", ""};
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
