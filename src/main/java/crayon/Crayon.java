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
            ui.showErrorMessage("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command != null) {
                return command.execute(storage, ui, taskList);
            } else {
                return "I'm sorry, but I didn't understand that command.";
            }
        } catch (CrayonException e) {
            return e.getMessage();
        }
    }

    public boolean saveOnExit() {
        return storage.saveTasksToCsv(taskList.getTasks());
    }
}
