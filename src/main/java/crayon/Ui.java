package crayon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Display initial greeting message when crayon.Crayon starts up.
    public void showWelcome() {
        String message = Constants.SEPARATOR
                + "Hello! I'm\n"
                + Constants.ASCII_NAME
                + "What can I do for you?\n"
                + Constants.SEPARATOR;

        System.out.println(message);
    }

    // Display farewell message when the conversation ends.
    public void showFarewell() throws IOException {
        String message = Constants.SEPARATOR
                + "Bye. Hope to see you again soon!\n"
                + Constants.SEPARATOR;

        System.out.println(message);

        // Close BufferedReader
        br.close();
    }

    public void showErrorMessage(String message) {
        String errorMessage = Constants.SEPARATOR
                + message
                + Constants.SEPARATOR;

        System.out.println(errorMessage);
    }

    public String readUserCommand() throws IOException {
        return br.readLine();
    }
}
