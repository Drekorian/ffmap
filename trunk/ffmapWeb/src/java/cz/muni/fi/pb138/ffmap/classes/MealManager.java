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
        if(id <= 0) throw new IllegalArgumentException("Id must be greater than zero!");

        String query = "for $meal in /fastfood-database/meals/meal[@id = " + id + "] " +
                       "return $meal";

        try{
            String queryResult = database.XQueryCommand(query);
            if((queryResult == null) || (queryResult.equals(""))) return null;
            Document document = XQueryResultController.getDocument(queryResult);
            return parseMeal(document.getDocumentElement());
        } catch (Exception ex){
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return null;
    }

    public IDatabaseStoreable find(String name){

        String query = "for $meal in /fastfood-database/meals/meal[lower-case(@name) = \"" + name.toLowerCase() + "\"] return $meal ";
        try {
            String queryResult = database.XQueryCommand(query);
            if((queryResult == null) || (queryResult.equals(""))) return null;
            Document document = XQueryResultController.getDocument(queryResult);
            return parseMeal(document.getDocumentElement());
        } catch (Exception ex) {
            Logger.getLogger(MealManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<? extends IDatabaseStoreable> getAll() {
        List<Meal> meals = null;
        
        String query = "let $meals := /fastfood-database/meals " +
                       "return $meals";
        try{
            String queryResult = database.XQueryCommand(query);
            Document document = XQueryResultController.getDocument(queryResult);
            meals = new ArrayList<Meal>();

            for(int i = 0; i < document.getDocumentElement().getElementsByTagName("meal").getLength(); i++){
                Meal meal = parseMeal((Element)document.getDocumentElement().getElementsByTagName("meal").item(i));
                if(meal != null){
                    meals.add(meal);
                }
            }
        } catch (Exception ex){
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return meals;

    }
    public long count() {
        long result = -1;
        String query = "let $meals := /fastfood-database/meals/meal return count($meals)";
        try {
            String queryResult = database.XQueryCommand(query);
            if((queryResult == null) || (queryResult.equals(""))) return result;
            result = Long.valueOf(queryResult);
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return result;
    }

    private Meal parseMeal(Element meal){

        Long _id = Long.valueOf(meal.getAttribute("id"));
        String _name = meal.getAttribute("name");
        String _description = null;
        if(meal.hasChildNodes()){
            _description = meal.getElementsByTagName("description").item(0).getTextContent();
        }

        return Meal.loadMeal(_id, _name, _description);
    }

    public boolean checkNameAvalilability(String name){
        String query = "let $meals := /fastfood-database/meals/meal" +
                       " return count($meals[lower-case(@name) = \"" + name.toLowerCase() + "\"])";
        try {
            long result = Long.valueOf(database.XQueryCommand(query));
            if(result != 0){
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }

}
