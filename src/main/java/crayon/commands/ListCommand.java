package crayon.commands;

import crayon.enums.Action;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(Action.LIST);
    }

    /**
     * Executes the command to list all tasks.
     *
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to list all tasks.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showMessage(taskList.listAllTasks());
    }
}
