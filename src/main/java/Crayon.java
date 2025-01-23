import java.util.ArrayList;
import java.util.Scanner;

public class Crayon {

    private static final String SEPARATOR = "________________________________________";
    private static final String ASCII_NAME = """
          ____ ____     _ __   _____  _   _
         / ___|  _ \\   / \\\\ \\ / / _ \\| \\ | |
        | |   | |_) | / _ \\\\ V | | | |  \\| |
        | |___|  _ < / ___ \\| || |_| | |\\  |
         \\____|_| \\_/_/   \\_|_| \\___/|_| \\_|
        
        """;

    private static ArrayList<String> taskList = new ArrayList<String>();

    public static void main(String[] args) {
        greetUser();
        echoMessage();
        sayGoodbye();
    }

    /**
     * Display initial greeting message when Crayon starts up.
     */
    private static void greetUser() {
        String message = SEPARATOR + "\n"
                + "Hello! I'm\n"
                + ASCII_NAME
                + "What can I do for you?\n"
                + SEPARATOR;

        System.out.println(message);
    }

    /**
     * Display farewell message when the conversation ends.
     */
    private static void sayGoodbye() {
        String message = SEPARATOR + "\n"
                + "Bye. Hope to see you again soon!\n"
                + SEPARATOR + "\n";

        System.out.println(message);
    }

    /**
     * Echoes the user message back.
     */
    private static void echoMessage() {

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String userInput = sc.nextLine();
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("TO BE IMPLEMENTED");
                } else {
                    taskList.add(userInput);
                    String message = SEPARATOR + "\n"
                            + "added: " + userInput +  "\n"
                            + SEPARATOR + "\n";

                    System.out.println(message);
                }
            }
        }
    }
}