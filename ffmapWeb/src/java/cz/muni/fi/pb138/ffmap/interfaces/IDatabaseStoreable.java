/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.interfaces;

/**
 * Interface implemented by classes that can be stored in database.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public interface IDatabaseStoreable {
    /**
     * Tries to save the database storeable object to the database.
     *
     * @return true when object is successfully saved, false otherwise
     */
    public boolean save();
    /**
     * Tries to delete the database storeable object from the database.
     *
     * @return true when object is successfully saved, false otherwise
     */
    public boolean destroy();
}
