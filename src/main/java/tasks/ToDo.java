package tasks;

import exceptions.CrayonInvalidFormatException;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description);
    }

    public static ToDo createToDoTask(String description) throws CrayonInvalidFormatException{
        if (description == null || description.trim().isEmpty()) {
            throw new CrayonInvalidFormatException("ToDo description cannot be empty");
        }
        return new ToDo(description);
    }

    @Override
    public String getType() {
        return "TODO";
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
