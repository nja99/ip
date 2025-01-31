package crayon.commands;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.enums.TaskType;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command{

    /**
     * The content of the event task.
     */
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
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to add the task.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        String eventMessage = taskList.createTask(TaskType.EVENT, content);
        ui.showTaskAction(eventMessage);
    }
}
