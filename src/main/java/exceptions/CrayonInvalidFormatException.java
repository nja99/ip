package exceptions;

public class CrayonInvalidFormatException extends Exception {

    public CrayonInvalidFormatException(String message) {
        super("Invalid Format! " + message);
    }
}
