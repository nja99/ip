package crayon.commands;

import crayon.enums.Action;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * This class represents a command to find tasks.
 */
public class FindCommand extends Command {

    private final String content;

    /**
     * Constructs a new FindCommand instance.
     *
     * @param content The content to find.
     */
    public FindCommand(String content) {
        super(Action.FIND);
        this.content = content;
    }

    /**
     * Executes the command to find tasks.
     *
     * @param storage The storage to save the task list to.
     * @param ui The user interface to interact with the user.
     * @param taskList The task list to find tasks from.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showMessage(taskList.listFilteredTasks(content));
    }
}
