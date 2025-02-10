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
     * @param storage The storage object to save the task to.
     * @param taskList The task list object to list tasks from.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return taskList.listAllTasks();
    }
}
