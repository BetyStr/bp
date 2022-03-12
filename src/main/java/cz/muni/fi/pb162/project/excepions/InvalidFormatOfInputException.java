package cz.muni.fi.pb162.project.excepions;

/**
 * @author Alzbeta Strompova
 */
public class InvalidFormatOfInputException extends RuntimeException {

    public InvalidFormatOfInputException() {
        super();
    }

    public InvalidFormatOfInputException(String message) {
        super(message);
    }

    public InvalidFormatOfInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
