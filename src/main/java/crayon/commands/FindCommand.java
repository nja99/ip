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
     * @param storage The storage object to save the task to.
     * @param taskList The task list object to find tasks from.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.getListFilteredTasksMessage(taskList.filterTasksByPattern(content));
    }
}
