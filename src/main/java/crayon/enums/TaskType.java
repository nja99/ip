package crayon.enums;

/**
 * Represents the type of task.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Returns the TaskType from the given string.
     *
     * @param value The string value to convert to TaskType.
     * @return The TaskType from the given string.
     */
    public static TaskType fromString(String value) {
        return switch (value.toLowerCase()) {
            case "todo" -> TaskType.TODO;
            case "deadline" -> TaskType.DEADLINE;
            case "event" -> TaskType.EVENT;
        default -> throw new IllegalArgumentException("Unknown TaskType: " + value);
        };
    }
}
