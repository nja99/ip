package crayon;

import java.io.IOException;

import crayon.command.Command;
import crayon.exceptions.CrayonException;

public class Crayon {

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

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

    public static void main(String[] args) {
        new Crayon().run();
    }

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
