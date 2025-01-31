package crayon.exceptions;

/**
 * Represents an exception when an invalid format is passed to a method.
 */
public class CrayonInvalidFormatException extends CrayonException {

    /**
     * Constructs a CrayonInvalidFormatException.
     *
     * @param message The message of the exception.
     */
    public CrayonInvalidFormatException(String message) {
        super("Invalid Format! " + message);
    }
}
