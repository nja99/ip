package crayon.commands;

import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super(Action.BYE);
    }

    /**
     * Executes the command to exit the program.
     *
     * @param storage The storage object to save the tasks to.
     * @param taskList The task list object to save the tasks from.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     * @throws CrayonIllegalArgumentException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws CrayonIllegalArgumentException {
        this.isExit = true;
        storage.saveTasksToCsv(taskList.getTasks());
        return ui.getFarewellMessage();
    }
}
