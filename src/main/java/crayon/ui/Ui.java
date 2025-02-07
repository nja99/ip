package crayon.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import crayon.Constants;

/**
 * This class represents the user interface of the application.
 */
public class Ui implements AutoCloseable {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Shows a message to the user.
     *
     * @param message The message to show.
     */
    public void showMessage(String message) {
        String messageWithSeparator = Constants.SEPARATOR
                + message
                + Constants.SEPARATOR;

        System.out.println(messageWithSeparator);
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        String messageWithSeparator = Constants.ERROR_SEPARATOR
                + message + "\n"
                + Constants.ERROR_SEPARATOR;
        System.out.println(messageWithSeparator);
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        String message = "Hello! I'm\n"
                + Constants.ASCII_NAME
                + "\nWhat can I do for you?\n";

        showMessage(message);
    }

    /**
     * Shows the farewell message to the user.
     */
    public void showFarewell() {
        showMessage("Bye. Hope to see you again soon!\n");
    }

    // Implementation to be Added
    public void showTaskAction(String message) {
        showMessage(message);
    }

    // Implementation to be Added
    public void showStatusAction(String message) {
        showMessage(message);
    }

    /**
     * Reads the user's command.
     *
     * @return The user's command.
     * @throws IOException If an I/O error occurs.
     */
    public String readUserCommand() throws IOException {
        return br.readLine();
    }

    /**
     * Closes the user interface.
     *
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        br.close();
    }
}
