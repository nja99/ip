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

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        String deleteMessage = taskList.deleteTask(Integer.parseInt(content));
        ui.showMessage(deleteMessage);
    }
}
