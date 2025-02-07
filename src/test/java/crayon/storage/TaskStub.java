package crayon.storage;

import crayon.tasks.Task;

public class TaskStub extends Task {

    private final String type;
    private final String[] csvRow;

    public TaskStub(String type, String description, boolean isDone, String[] csvRow) {
        super(description, isDone);
        this.type = type;
        this.csvRow = csvRow;
    }

    @Override
    public String[] toCsvRow() {
        return csvRow;
    }

    @Override
    public String getType() {
        return type;
    }
}
