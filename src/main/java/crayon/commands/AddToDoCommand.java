package crayon.commands;

import crayon.enums.Action;
import crayon.enums.TaskType;
import crayon.exceptions.CrayonTaskCreationException;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.tasks.Task;
import crayon.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class AddToDoCommand extends Command {

    private final String content;

    /**
     * Constructs an AddToDoCommand.
     *
     * @param content The content of the todo task.
     */
    public AddToDoCommand(String content) {
        super(Action.TODO);
        this.content = content;
    }

    /**
     * Executes the command to add a todo task.
     *
     * @param storage The storage to save the task list.
     * @param taskList The task list to add the task to.
     * @param ui The user interface to interact with the user.
     * @return The response to the user.
     * @throws CrayonTaskCreationException If the task creation fails.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws CrayonTaskCreationException {
        Task task = taskList.createTask(TaskType.TODO, content);
        return ui.getTaskAddedMessage(task, taskList.getSize());
    }
}
