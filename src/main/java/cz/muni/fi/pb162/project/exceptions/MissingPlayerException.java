package cz.muni.fi.pb162.project.exceptions;

/**
 * Exception is thrown when you do not have enough players to play game. <p>
 * It is unchecked exceptions. Unchecked exceptions do not need to be declared
 * in a method or constructor's throws clause if they can be thrown by the execution
 * of the method or constructor and propagate outside the method or constructor boundary.
 *
 * @author Alzbeta Strompova
 */
public class MissingPlayerException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized
     * by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public MissingPlayerException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause. Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public MissingPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

}
