package cz.muni.fi.pb162.project.excepions;

/**
 * Self-documenting
 *
 * @author Alzbeta Strompova
 */
public class NotAllowedMoveException extends RuntimeException {

    public NotAllowedMoveException(String message) {
        super(message);
    }

    public NotAllowedMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
