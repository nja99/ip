package exceptions;

public class CrayonUnsupportedTaskException extends Exception {

    public CrayonUnsupportedTaskException() {
        super("Sorry, I do not recognize the specified task type. Please check the task type and ensure it is valid.");
    }
}
