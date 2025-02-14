package crayon.parser;

import crayon.commands.AddDeadlineCommand;
import crayon.commands.AddEventCommand;
import crayon.commands.AddToDoCommand;
import crayon.commands.ByeCommand;
import crayon.commands.Command;
import crayon.commands.DeleteCommand;
import crayon.commands.FindCommand;
import crayon.commands.ListCommand;
import crayon.commands.MarkCommand;
import crayon.commands.UnmarkCommand;
import crayon.enums.Action;
import crayon.exceptions.CrayonUnsupportedTaskException;

/**
 * This class is responsible for parsing user input into commands.
 */
public class Parser {

    /**
     * Parses the user input into a command.
     *
     * @param userInput The user input.
     * @return The command that corresponds to the user input.
     */
    public static Command parseCommand(String userInput) {

        try {
            Action action = parseAction(userInput);
            String content = parseContent(userInput);
            return createCommand(action, content);
        } catch (CrayonUnsupportedTaskException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Action parseAction(String userInput) throws CrayonUnsupportedTaskException {
        String[] args = userInput.split(" ", 2);
        try {
            return Action.fromString(args[0]);
        } catch (CrayonUnsupportedTaskException e) {
            throw new CrayonUnsupportedTaskException("Unsupported action: " + args[0]);
        }
    }

    private static String parseContent(String userInput) {
        String[] args = userInput.split(" ", 2);
        return args.length > 1 ? args[1].trim() : "";
    }

    private static Command createCommand(Action action, String content) {
        return switch (action) {
            case LIST -> new ListCommand();
            case FIND -> new FindCommand(content);
            case TODO -> new AddToDoCommand(content);
            case DEADLINE -> new AddDeadlineCommand(content);
            case EVENT -> new AddEventCommand(content);
            case DELETE -> new DeleteCommand(content);
            case MARK -> new MarkCommand(content);
            case UNMARK -> new UnmarkCommand(content);
            case BYE -> new ByeCommand();
        };
    }
}
