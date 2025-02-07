package crayon.commands;

import crayon.enums.Action;
import crayon.enums.TaskType;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
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
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to add the task.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        String todoMessage = taskList.createTask(TaskType.TODO, content);
        ui.showTaskAction(todoMessage);
    }
}
