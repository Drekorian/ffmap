package cz.muni.fi.pb138.ffmap.exceptions;

/**
 * An Exception thrown when a Meal class or its instance reaches an illegal
 * state or its manipulation fails.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class MealException extends Exception {
    public MealException(Throwable cause) {
        super(cause);
    }
    public MealException(String message, Throwable cause) {
        super(message, cause);
    }
    public MealException(String message) {
        super(message);
    }
    public MealException() {
    }
}
