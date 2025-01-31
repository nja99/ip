package crayon.enums;

public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    public static TaskType fromString(String value) {
        return switch (value.toLowerCase()) {
        case "todo" -> TaskType.TODO;
        case "deadline" -> TaskType.DEADLINE;
        case "event" -> TaskType.EVENT;
        default -> throw new IllegalArgumentException("Unknown TaskType: " + value);
        };
    }
}
