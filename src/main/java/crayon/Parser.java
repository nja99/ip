package crayon;

import crayon.command.*;

import crayon.enums.Action;
import crayon.exceptions.CrayonUnsupportedTaskException;

public class Parser {

    public static Command parseCommand(String userInput) {
        String[] args = userInput.split(" ", 2);
        String content = "";

        if (args.length > 1) content = args[1].trim();
        try {
            Action action = Action.fromString(args[0]);
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
        } catch (CrayonUnsupportedTaskException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
