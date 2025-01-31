package crayon.exceptions;

/**
 * Represents an exception when an unsupported task type is passed to a method.
 */
public class CrayonUnsupportedTaskException extends CrayonException {

    /**
     * Constructs a CrayonUnsupportedTaskException.
     *
     * @param message The message of the exception.
     */
    public CrayonUnsupportedTaskException(String message) {
        super("Sorry, I do not recognize the specified task type. Please check the task type and ensure it is valid.\n" + message);
    }
}
