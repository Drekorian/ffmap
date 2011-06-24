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
import javax.xml.parsers.ParserConfigurationException;
import org.basex.core.BaseXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Stash
 */
public class MealManager implements IDatabaseManager {

    private static MealManager instance = null;
    private DBHandler database;

    public static MealManager getInstance() throws DatabaseInitException{
        if(instance == null){
            instance = new MealManager();
        }
        return instance;
    }

    private MealManager() throws DatabaseInitException{
        database = DBHandler.getInstance();
    }

    public IDatabaseStoreable find(long id) {
        try {
            String queryResult = database.XQueryCommand("for $meal in //meals return $meal");
            System.out.println(queryResult);
            Document doc = XQueryResultController.getDocument(queryResult);
            Element node = doc.getElementById(String.valueOf(id));
            if(node == null) System.out.println("Fail!");
            long mealId = Long.valueOf(node.getAttribute("id"));
            String name = node.getAttribute("name");
            String description = node.getFirstChild().getNodeValue();
            Meal meal = new Meal(name, null, description);
            return meal;
        } catch (SAXException ex) {
            Logger.getLogger(MealManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MealManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BaseXException ex) {
            Logger.getLogger(MealManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MealManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            return null;
        }
    }

    public List<IDatabaseStoreable> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
