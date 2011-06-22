/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseManager;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;

/**
 * Class managing data retrievel o
 *
 * @author Marek Osvald
 * @version 2011.0622
 */
public class JointManager implements IDatabaseManager {
    private static JointManager instance = null;

    public static JointManager getInstance() {
        if (instance == null) {
            instance = new JointManager();
        }

        return instance;
    }

    private JointManager() {
    }

    public IDatabaseStoreable find(long id) {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public List<IDatabaseStoreable> getAll() {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public List<IDatabaseStoreable> count() {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * TODO: DELETE ME
     * @return
     */
    public String justTesting() {
        String result = null;
        try {
            DBHandler.getInstance().setSession("localhost", 1984, "admin", "admin");
            DBHandler.getInstance().openDatabase("ffmap");
            result = DBHandler.getInstance().XQueryCommand(
                "for $joint in db:open('ffmap')//joint//mon " +
                "return $joint"
            );

        } catch (Exception ex) {
            Logger.getLogger(JointManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return result;
    }
}
