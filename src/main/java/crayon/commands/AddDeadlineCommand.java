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
     * @param taskList The task list object to add the task to.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return taskList.createTask(TaskType.DEADLINE, content);
    }
}
