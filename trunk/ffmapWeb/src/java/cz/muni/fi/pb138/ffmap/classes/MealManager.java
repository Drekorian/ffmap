/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseManager;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.ArrayList;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<? extends IDatabaseStoreable> getAll() {
        List<Meal> result = new ArrayList<Meal>();

        Document queryResult = XQueryResultController.getDocument(database.XQueryCommand("/fastfood-database/meals"));

    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private parseMeal(Element meal, NodeList prices){
        Meal result = null;

        Long _id = Long.valueOf(meal.getAttribute("id"));
        String _name = meal.getAttribute("name");
        if(meal.hasChildNodes()){
            String _description = meal.getElementsByTagName("description").item(0).getTextContent();
        }

    }

}
