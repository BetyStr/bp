package cz.muni.fi.pb162.project.excepions;

/**
 * @author Alzbeta Strompova
 */
public class MissingPlayerException extends RuntimeException {

    public MissingPlayerException() {
        super();
    }

    public MissingPlayerException(String message) {
        super(message);
    }

    public MissingPlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
