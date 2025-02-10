package crayon.commands;

import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final String content;

    /**
     * Constructs a MarkCommand.
     *
     * @param content The content of the task to be marked as done.
     */
    public MarkCommand(String content) {
        super(Action.MARK);
        this.content = content;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param storage The storage object to save the task to.
     * @param taskList The task list object to mark the task as done.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     * @throws CrayonIllegalArgumentException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws CrayonIllegalArgumentException {
        return taskList.markTaskAsDone(Integer.parseInt(content));
    }
}
