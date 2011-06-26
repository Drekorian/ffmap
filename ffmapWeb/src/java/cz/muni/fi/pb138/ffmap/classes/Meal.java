package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;

/**
 * Class representing a meal served by fast-food restaurants.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class Meal implements IDatabaseStoreable {
    private Long id;
    private String name;
    private String description;

    public static Meal loadMeal(Long id, String name, String description){
        return new Meal(id, name, description);
    }

    private Meal(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Parametric constructor.
     *
     * @param name meal's name
     * @param prices prices of the meal during the day
     * @param description meal's description
     */
    public Meal(String name, String description) {
        this(null, name, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass() || name == null) {
            return false;
        }
        
        final Meal other = (Meal)obj;

        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return name + "[" + id + "] " + description;
    }

    public boolean save() {
        if(id == null){
            return insert();
        }
        return update();
    }

    public boolean destroy() {
        if(id == null){
            return false;
        }

        String command = "for $meal in /fastfood-database/meals/meal[@id = "+
                + id + "] return delete node $meal";
        try {
            DBHandler.getInstance().XQueryCommand(command);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Meal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void keyIncrement() throws BaseXException, DatabaseInitException, IOException{
        DBHandler.getInstance().keyIncrement("meals-key-number");
    }

    private boolean insert(){
        try {

            keyIncrement();
            String idQuery = "let $id := /fastfood-database/@meals-key-number " + "return number($id)";

            if ((id = Long.valueOf(DBHandler.getInstance().XQueryCommand(idQuery))) == null) {
                return false;
            }

            String command = "let $meals := /fastfood-database/meals " + "return insert node " + "<meal id=\"" + id + "\" name=\"" + name + "\"> ";
            if (description != null) {
                command = command.concat("<description>" + description + "</description> ");
            }
            command = command.concat("</meal> as last into $meals ");

            DBHandler.getInstance().XQueryCommand(command);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Meal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
                                
    }

    private boolean update(){
        String command = "let $meal := /fastfood-database/meals/meal[@id=" + id + "]" +
                         "return (" +
                            "replace value of node $meal/@name with \"" + name + "\" ";
        
        if(description != null){
            command = command.concat(", if(exists($meal/description)) then " +
                    "replace value of node $meal/description with \"" + description + "\" " +
                    "else insert node <description>" + description + "</description> into $meal)");
        } else {
            command = command.concat(", if(exists($meal/description)) then " +
                    "delete node $meal/description else () )");
        }
        try {
            DBHandler.getInstance().XQueryCommand(command);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Meal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
}
