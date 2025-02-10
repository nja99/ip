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
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to add the task.
     * @throws CrayonIllegalArgumentException If there is an invalid argument.
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        this.isExit = true;
        storage.saveTasksToCsv(taskList.getTasks());
        return "Bye!";
    }
}
