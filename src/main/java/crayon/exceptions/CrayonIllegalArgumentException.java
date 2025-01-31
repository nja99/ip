package crayon.exceptions;

/**
 * Represents an exception when an illegal argument is passed to a method.
 */
public class CrayonIllegalArgumentException extends CrayonException {

    /**
     * Constructs a CrayonIllegalArgumentException.
     *
     * @param message The message of the exception.
     */
    public CrayonIllegalArgumentException(String message) {
        super(message);
    }
}
