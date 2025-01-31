package crayon.commands;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {

    private final String content;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param content The content of the task to be unmarked as done.
     */
    public UnmarkCommand(String content) {
        super(Action.UNMARK);
        this.content = content;
    }

    /**
     * Executes the command to unmark a task as done.
     *
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to unmark the task as done.
     * @throws CrayonIllegalArgumentException If the content is invalid.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        String unmarkMessage = taskList.markTaskAsUndone(Integer.parseInt(content));
        ui.showStatusAction(unmarkMessage);
    }
}
