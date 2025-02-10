package crayon.commands;

import crayon.enums.Action;
import crayon.exceptions.CrayonException;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    protected final Action action;
    protected boolean isExit;

    /**
     * Constructs a Command.
     *
     * @param action The action of the command.
     */
    public Command(Action action) {
        this.action = action;
        this.isExit = false;
    }

    /**
     * Executes the command.
     *
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to add the task.
     * @throws CrayonException If there is an exception.
     */
    public abstract String execute(Storage storage, Ui ui, TaskList taskList) throws CrayonException;
}
