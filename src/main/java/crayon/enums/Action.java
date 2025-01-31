package crayon.enums;

import crayon.exceptions.CrayonUnsupportedTaskException;

/**
 * Represents the action of a command.
 */
public enum Action {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    BYE;

    /**
     * Returns the Action from the given string.
     *
     * @param value The string value to convert to Action.
     * @return The Action from the given string.
     * @throws CrayonUnsupportedTaskException If the string is not a valid Action.
     */
    public static Action fromString(String value) throws CrayonUnsupportedTaskException {
        return switch (value.toLowerCase()) {
        case "list" -> Action.LIST;
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
