/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.exceptions;

/**
 *
 * @author Stash
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
