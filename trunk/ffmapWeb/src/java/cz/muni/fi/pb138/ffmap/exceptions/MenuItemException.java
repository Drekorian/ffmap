package cz.muni.fi.pb138.ffmap.exceptions;

/**
 * An Exception thrown when a Meal class or its instance reaches an illegal
 * state or its manipulation fails.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class MenuItemException extends Exception {
    public MenuItemException(Throwable cause) {
        super(cause);
    }
    public MenuItemException(String message, Throwable cause) {
        super(message, cause);
    }
    public MenuItemException(String message) {
        super(message);
    }
    public MenuItemException() {
    }
}
