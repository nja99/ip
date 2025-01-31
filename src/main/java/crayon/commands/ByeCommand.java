package crayon.commands;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;

import java.io.IOException;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand() {
        super(Action.BYE);
    }

    /**
     * Executes the command to exit the program.
     *
     * @param storage The storage to save the task.
     * @param ui The user interface to show messages.
     * @param taskList The task list to add the task.
     * @throws CrayonIllegalArgumentException If there is an invalid argument.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        this.isExit = true;
        try {
            storage.saveTasksToCSV(taskList.getTasks());
            ui.showFarewell();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
