package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseManager;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class managing the Meal class data retrieval.
 *
 * @author Aleksandar Zivkovic
 * @version 2011.0624
 */

public class MealManager implements IDatabaseManager {
    private static MealManager instance = null;
    private DBHandler database;

    public static MealManager getInstance() throws DatabaseInitException {
        if(instance == null){
            instance = new MealManager();
        }
        
        return instance;
    }

    /**
     * Parameterless constructor. Private in order to force the class to be
     * singleton.
     */
    private MealManager() throws DatabaseInitException {
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
        } catch (Exception ex) {
            Logger.getLogger(MealManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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
