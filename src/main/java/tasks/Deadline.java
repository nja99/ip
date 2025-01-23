package tasks;

public class Deadline extends Task{

    private String endDate;

    /**
     * Constructs a tasks.Deadline task by parsing the provided description string.
     * The expected format is: <task description> /by <deadline date>.
     *
     * @param description The description string containing the task and deadline.
     */
    public Deadline(String description) {
        super(description);
        parse(description);
    }

    @Override
    public void parse(String description) {
        String[] parts = description.split(" /by ");
        if(parts.length == 2) {
            this.description = parts[0].trim();
            this.endDate = parts[1].trim();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate + ")";
    }
}
