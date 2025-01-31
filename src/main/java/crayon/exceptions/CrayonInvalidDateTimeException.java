package crayon.exceptions;

/**
 * Represents an exception when an invalid date and time format is passed to a method.
 */
public class CrayonInvalidDateTimeException extends CrayonInvalidFormatException {

    /**
     * Constructs a CrayonInvalidDateTimeException.
     *
     * @param message The message of the exception.
     */
    public CrayonInvalidDateTimeException(String message) {
        super(message);
    }
}
