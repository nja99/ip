package crayon;

import java.io.IOException;

import crayon.commands.Command;
import crayon.exceptions.CrayonException;

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

    /**
     * The main entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Crayon().run();
    }

    /**
     * Runs the main loop of the application.
     */
    public void run() {
        try (Ui autoCloseUi = this.ui) { // This will automatically close the UI when exiting the try block
            autoCloseUi.showWelcome();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String userCommand = autoCloseUi.readUserCommand();
                    Command command = Parser.parseCommand(userCommand);
                    if (command != null) {
                        command.execute(storage, autoCloseUi, taskList);
                        isExit = command.exitRequested();
                    }
                } catch (IOException e) {
                    autoCloseUi.showErrorMessage("IO error: " + e.getMessage());
                } catch (CrayonException e) {
                    autoCloseUi.showErrorMessage(e.getMessage());
                }
            }
        } catch (Exception e) {
            ui.showErrorMessage("Error closing UI: " + e.getMessage());
        }
    }
}
