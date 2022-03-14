package cz.muni.fi.pb162.project.excepions;

/**
 * @author Alzbeta Strompova
 */
public class EmptySquareException extends Exception {

    public EmptySquareException(String message) {
        super(message);
    }

    public EmptySquareException(String message, Throwable cause) {
        super(message, cause);
    }
}
