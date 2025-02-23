package crayon.enums;

import crayon.exceptions.CrayonUnsupportedTaskException;

/**
 * Represents the action of a command.
 */
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

    /**
     * Returns the Action from the given string.
     *
     * @param value The string value to convert to Action.
     * @return The Action from the given string.
     * @throws CrayonUnsupportedTaskException If the string is not a valid Action.
     */
    public static Action fromString(String value) throws CrayonUnsupportedTaskException {
        return switch (value.toLowerCase()) {
            case "list", "ls" -> Action.LIST;
            case "find", "f" -> Action.FIND;
            case "todo", "td" -> Action.TODO;
            case "deadline", "dl" -> Action.DEADLINE;
            case "event", "e" -> Action.EVENT;
            case "delete", "del" -> Action.DELETE;
            case "mark", "m" -> Action.MARK;
            case "unmark", "um" -> Action.UNMARK;
            case "bye", "exit", "q" -> Action.BYE;
            default -> throw new CrayonUnsupportedTaskException("Unknown Action: " + value);
        };
    }
}
