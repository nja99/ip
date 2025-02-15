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

    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param storage The storage object to save the tasks to.
     * @param taskList The task list object to save the tasks from.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     * @throws CrayonException If an error occurs during the execution of the command.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws CrayonException;
}
