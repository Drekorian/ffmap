/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.interfaces;

import java.util.List;

/**
 * Interface implemented by classes managin the database storeable objects.
 *
 * @author Marek Osvald
 * @2011.0622
 */
public interface IDatabaseManager {
    /**
     * Tries to find and retrive a database storeable object in the database.
     *
     * @param id unique ID of the database storeable object
     * @return a database storeable object if found, null otherwise
     */
    public IDatabaseStoreable find(long id);
    /**
     * Retrieves all database storeable objects (of current type) from the database.
     *
     * @return list of all database storeable objects (of current type) in the database.
     */
    public List<? extends IDatabaseStoreable> getAll();
    /**
     * Retrieves the count of all database objects (of current type) in the database.
     *
     * @return count of all database storeable objects in the database, or -1
     * provided that the operation fails
     */
    public long count();
}
