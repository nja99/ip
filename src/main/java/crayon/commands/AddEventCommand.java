package crayon.commands;

import crayon.enums.Action;
import crayon.enums.TaskType;
import crayon.exceptions.CrayonTaskCreationException;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.tasks.Task;
import crayon.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {

    private final String content;

    /**
     * Constructs an AddEventCommand.
     *
     * @param content The content of the event task.
     */
    public AddEventCommand(String content) {
        super(Action.EVENT);
        this.content = content;
    }

    /**
     * Executes the command to add an event task.
     *
     * @param storage The storage to save the task list.
     * @param taskList The task list to add the task to.
     * @param ui The user interface to interact with the user.
     * @return The response to the user.
     * @throws CrayonTaskCreationException If the task creation fails.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws CrayonTaskCreationException {
        Task task = taskList.createTask(TaskType.EVENT, content);
        return ui.getTaskAddedMessage(task, taskList.getSize());
    }
}
