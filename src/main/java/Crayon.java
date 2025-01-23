import exceptions.CrayonUnsupportedTaskException;
import tasks.*;

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
                String[] args = userInput.split(" ", 2);
                String taskDescription = "";

                if(args.length > 1) taskDescription = args[1].trim();

                try {
                    switch (args[0]) {
                        case "list":
                            TaskManager.listTasks();
                            break;
                        case "todo":
                            TaskManager.addTask(ToDo.createToDoTask(taskDescription));
                            break;
                        case "deadline":
                            TaskManager.addTask(Deadline.createDeadlineTask(taskDescription));
                            break;
                        case "event":
                            TaskManager.addTask(Event.createEventTask(taskDescription));
                            break;
                        case "delete":
                            TaskManager.deleteTask(Integer.parseInt(args[1]));
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
                            throw new CrayonUnsupportedTaskException();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}