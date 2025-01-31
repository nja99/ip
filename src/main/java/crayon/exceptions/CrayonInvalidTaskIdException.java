package crayon.exceptions;

/**
 * Represents an exception when an invalid task ID is passed to a method.
 */
public class CrayonInvalidTaskIdException extends CrayonIllegalArgumentException {
    /**
     * Constructs a CrayonInvalidTaskIdException.
     *
     * @param message The message of the exception.
     */
    public CrayonInvalidTaskIdException(String message) {
        super(message);
    }
}
