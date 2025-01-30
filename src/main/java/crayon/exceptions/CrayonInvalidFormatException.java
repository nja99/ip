package crayon.exceptions;

public class CrayonInvalidFormatException extends CrayonException {

    public CrayonInvalidFormatException(String message) {
        super("Invalid Format! " + message);
    }
}
