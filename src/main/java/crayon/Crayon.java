package crayon;

import java.io.IOException;

import crayon.commands.Command;
import crayon.exceptions.CrayonException;
import crayon.parser.Parser;
import crayon.storage.Storage;
import crayon.tasklist.TaskList;
import crayon.ui.Ui;

/**
 * Crayon is a simple task manager that allows users to manage their tasks.
 */
public class Crayon {

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Crayon instance.
     */
    public Crayon() {
        ui = new Ui();
        storage = new Storage();

        try {
            taskList = new TaskList(storage.loadTasksFromCsv());
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Gets the response to the user input.
     *
     * @param input The user input.
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command != null) {
                return command.execute(storage, taskList, ui);
            } else {
                return ui.getUnknownCommandMessage();
            }
        } catch (CrayonException e) {
            return e.getMessage();
        }
    }

    /**
     * Shows the welcome message.
     *
     * @return The welcome message.
     */
    public String showWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Saves the tasks to the storage file when the application exits.
     *
     * @return True if the tasks were saved successfully, false otherwise.
     */
    public boolean saveOnExit() {
        return storage.saveTasksToCsv(taskList.getTasks());
    }
}
