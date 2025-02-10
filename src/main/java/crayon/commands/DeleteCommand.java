package crayon.commands;

import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private final String content;

    /**
     * Constructs a DeleteCommand.
     *
     * @param content The content of the task to be deleted.
     */
    public DeleteCommand(String content) {
        super(Action.DELETE);
        this.content = content;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param storage The storage object to save the task to.
     * @param taskList The task list object to delete the task from.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     * @throws CrayonIllegalArgumentException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws CrayonIllegalArgumentException {
        return taskList.deleteTask(Integer.parseInt(content));
    }
}
