package crayon.commands;

import crayon.enums.Action;
import crayon.enums.TaskType;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    private final String taskType;

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(Action.LIST);
        taskType = "all";
    }

    /**
     * Constructs a ListCommand with a specified task type.
     *
     * @param taskTypes The task type to list.
     */
    public ListCommand(String taskTypes) {
        super(Action.LIST);
        this.taskType = taskTypes;
    }

    /**
     * Executes the command to list all tasks.
     *
     * @param storage The storage object to save the task to.
     * @param taskList The task list object to list tasks from.
     * @param ui The user interface object to interact with the user.
     * @return The response to the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        if (taskType.equalsIgnoreCase("all")) {
            return ui.getListAllMessage(taskList.getTasks());
        }

        if (!isValidTaskType()) {
            return ui.getUnknownCommandMessage();
        }

        return ui.getListFilteredTypes(taskList.filterTasksByType(taskType), taskType);
    }

    /**
     * Validates the provided task type.
     *
     * @return True if the task type is valid, false otherwise.
     */
    private boolean isValidTaskType() {
        try {
            TaskType.fromString(taskType);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid task type: " + taskType);
            return false;
        }
    }
}
