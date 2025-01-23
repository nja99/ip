package enums;

public enum Action {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    BYE;

    public static Action fromString(String value) {
        return switch (value.toLowerCase()) {
            case "list" -> Action.LIST;
            case "todo" -> Action.TODO;
            case "deadline" -> Action.DEADLINE;
            case "event" -> Action.EVENT;
            case "delete" -> Action.DELETE;
            case "mark" -> Action.MARK;
            case "unmark" -> Action.UNMARK;
            case "bye" -> Action.BYE;
            default -> throw new IllegalArgumentException("Unknown enums.Action: " + value);
        };
    }
}
