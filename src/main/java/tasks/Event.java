package tasks;

public class Event extends Task{

    private String startTime;
    private String endTime;

    public Event(String description) {
        super(description);
        parse(description);
    }

    @Override
    public void parse(String description) {
        String[] parts = description.split(" /from ");
        if(parts.length == 2) {
            this.description = parts[0].trim();

            String[] timeParts = parts[1].split(" /to ");
            if(timeParts.length == 2) {
                this.startTime = timeParts[0].trim();
                this.endTime = timeParts[1].trim();
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
