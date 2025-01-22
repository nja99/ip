public class Crayon {
    public static void main(String[] args) {
        greeting();
        exit();
    }

    public static void greeting() {
        String message = "________________________________________\n"
                + "Hello! I'm Crayon\n"
                + "What can I do for you?\n"
                + "________________________________________";

        System.out.println(message);
    }

    public static void exit() {
        String message = "Bye. Hope to see you again soon!\n"
                + "________________________________________\n";
        System.out.println(message);
    }
}
