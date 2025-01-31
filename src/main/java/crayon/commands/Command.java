package crayon.commands;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonException;

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
     * Returns the exit status of the command.
     *
     * @return The exit status of the command.
     */
    public boolean exitRequested() {
        return this.isExit;
    }

    /**
     * Executes the command.
     *
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to add the task.
     * @throws CrayonException If there is an exception.
     */
    public abstract void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonException;
}
