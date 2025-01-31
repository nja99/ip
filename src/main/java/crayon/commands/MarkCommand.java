package crayon.commands;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final String content;

    /**
     * Constructs a MarkCommand.
     *
     * @param content The content of the task to be marked as done.
     */
    public MarkCommand(String content) {
        super(Action.MARK);
        this.content = content;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to mark the task as done.
     * @throws CrayonIllegalArgumentException If the content is invalid.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        String markMessage = taskList.markTaskAsDone(Integer.parseInt(content));
        ui.showStatusAction(markMessage);
    }
}
