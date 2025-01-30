package crayon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void showMessage(String message) {
        String messageWithSeparator = Constants.SEPARATOR
                + message
                + Constants.SEPARATOR;

        System.out.println(messageWithSeparator);
    }

    public void showErrorMessage(String message) {
        String messageWithSeparator = Constants.ERROR_SEPARATOR
                + message + "\n"
                + Constants.ERROR_SEPARATOR;
        System.out.println(messageWithSeparator);
    }

    // Display initial greeting message when Crayon starts up.
    public void showWelcome() {
        String message = "Hello! I'm\n"
                + Constants.ASCII_NAME
                + "What can I do for you?\n";

        showMessage(message);
    }

    // Display farewell message when the conversation ends.
    public void showFarewell(){
        showMessage("Bye. Hope to see you again soon!\n");
    }

    public void showTaskAction(String message) {
        showMessage(message);
    }

    public void showStatusAction(String message) {
        showMessage(message);
    }

    public String readUserCommand() throws IOException {
        return br.readLine();
    }

    public void closeUi() throws IOException {
        br.close();
    }
}
