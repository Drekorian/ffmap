package cz.muni.fi.pb138.ffmap.exceptions;

/**
 * An Exception thrown when database initialization fails.
 *
 * @author Aleksandar Zivkovic
 * @version 2011.0626
 */

public class DatabaseInitException extends Exception {
    public DatabaseInitException(Throwable cause) {
        super(cause);
    }
    public DatabaseInitException(String message, Throwable cause) {
        super(message, cause);
    }
    public DatabaseInitException(String message) {
        super(message);
    }
    public DatabaseInitException() {
    }
}
