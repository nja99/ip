package crayon.commands;

import crayon.enums.Action;
import crayon.enums.TaskType;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {

    private final String content;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param content The content of the deadline task.
     */
    public AddDeadlineCommand(String content) {
        super(Action.DEADLINE);
        this.content = content;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param storage The storage object to save the task to.
     * @param ui The ui object to interact with the user.
     * @param taskList The taskList object to add the task to.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        String deadlineMessage = taskList.createTask(TaskType.DEADLINE, content);
        ui.showTaskAction(deadlineMessage);
    }
}
