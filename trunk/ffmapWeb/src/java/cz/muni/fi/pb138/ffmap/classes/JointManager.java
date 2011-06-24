/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseManager;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;

/**
 * Class managing data retrieval of the Joint class.
 *
 * @author Marek Osvald
 * @version 2011.0622
 */
public class JointManager implements IDatabaseManager {
    private static JointManager instance = null;
    private DBHandler database;

    public static JointManager getInstance() throws DatabaseInitException {
        if (instance == null) {
            instance = new JointManager();
        }

        return instance;
    }

    private JointManager() throws DatabaseInitException {
        database = DBHandler.getInstance();
    }

    public IDatabaseStoreable find(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<IDatabaseStoreable> getAll() {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public long count() {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addUserTest() throws BaseXException, IOException{
        database.XQueryCommand("insert node (<meal>{(attribute{'id'}{4})(attribute{'name'}{Grcek})}</meal>)");
    }

    /**
     * TODO: DELETE ME
     * @return
     */
    public String justTesting() {
        String result = null;
        try {
            //database.switchToTest();
            result = database.XQueryCommand(
                "for $joint in //joints/joint return $joint"
            );

        } catch (Exception ex) {
            Logger.getLogger(JointManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return result;
    }
}
