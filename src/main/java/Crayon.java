import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Crayon {

    private static boolean isBye = false;

    public static void main(String[] args) {
        greetUser();
        echoMessage();
    }

    // Display initial greeting message when Crayon starts up.
    private static void greetUser() {
        String message = Constants.SEPARATOR
                + "Hello! I'm\n"
                + Constants.ASCII_NAME
                + "What can I do for you?\n"
                + Constants.SEPARATOR;

        System.out.println(message);
    }

    // Display farewell message when the conversation ends.
    private static void sayGoodbye() {
        isBye = true;

        String message = Constants.SEPARATOR
                + "Bye. Hope to see you again soon!\n"
                + Constants.SEPARATOR;

        System.out.println(message);
    }

    // Echoes the user message back.
    private static void echoMessage() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!isBye) {
                String userInput = br.readLine();
                String[] args = userInput.split(" ");

                switch (args[0]) {
                    case "list":
                        TaskManager.listTasks();
                        break;
                    case "mark":
                        TaskManager.markTaskAsDone(Integer.parseInt(args[1]));
                        break;
                    case "unmark":
                        TaskManager.markTaskAsUndone(Integer.parseInt(args[1]));
                        break;
                    case "bye":
                        sayGoodbye();
                        break;
                    default:
                        TaskManager.addTask(userInput);
                        String message = Constants.SEPARATOR
                                + "added: " + userInput +  "\n"
                                + Constants.SEPARATOR;
                        System.out.println(message);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}