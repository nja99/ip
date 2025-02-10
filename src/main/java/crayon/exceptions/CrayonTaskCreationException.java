package crayon.exceptions;

/**
 * Represents an exception that occurs when creating a task.
 */
public class CrayonTaskCreationException extends CrayonException {
    /**
     * Constructs a new CrayonTaskCreationException instance with the specified message.
     *
     * @param message The message of the exception.
     */
    public CrayonTaskCreationException(String message) {
        super(message);
    }
}
