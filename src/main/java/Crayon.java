import java.util.Scanner;

public class Crayon {

    public static void main(String[] args) {
        greetUser();
        echoMessage();
        sayGoodbye();
    }

    /**
     * Display initial greeting message when Crayon starts up.
     */
    private static void greetUser() {
        String message = Constants.SEPARATOR + "\n"
                + "Hello! I'm\n"
                + Constants.ASCII_NAME
                + "What can I do for you?\n"
                + Constants.SEPARATOR + "\n";

        System.out.println(message);
    }

    /**
     * Display farewell message when the conversation ends.
     */
    private static void sayGoodbye() {
        String message = Constants.SEPARATOR + "\n"
                + "Bye. Hope to see you again soon!\n"
                + Constants.SEPARATOR + "\n";

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
                    TaskManager.listTasks();
                } else {
                    TaskManager.addTask(userInput);
                    String message = Constants.SEPARATOR + "\n"
                            + "added: " + userInput +  "\n"
                            + Constants.SEPARATOR + "\n";
                    System.out.println(message);
                }
            }
        }
    }
}