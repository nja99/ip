package crayon.enums;

import crayon.exceptions.CrayonUnsupportedTaskException;

public enum Action {
    LIST,
    FIND,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    BYE;

    public static Action fromString(String value) throws CrayonUnsupportedTaskException {
        return switch (value.toLowerCase()) {
            case "list" -> Action.LIST;
            case "find" -> Action.FIND;
            case "todo" -> Action.TODO;
            case "deadline" -> Action.DEADLINE;
            case "event" -> Action.EVENT;
            case "delete" -> Action.DELETE;
            case "mark" -> Action.MARK;
            case "unmark" -> Action.UNMARK;
            case "bye" -> Action.BYE;
            default -> throw new CrayonUnsupportedTaskException("Unknown Action: " + value);
        };
    }
}
