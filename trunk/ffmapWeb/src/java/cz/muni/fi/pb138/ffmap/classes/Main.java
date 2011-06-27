package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.enums.Role;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.List;
import org.basex.core.BaseXException;

/**
 *
 * @author Stash
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatabaseInitException, BaseXException, IOException {

        System.out.println("User authentication:");
        User usr = new User("stash", "sefolo", Role.USER, "sm", "bd");
        usr.save();
        System.out.println("Username: stash, Password: sefolo, Result: " + User.authenticate("stash", "sefolo"));
        System.out.println("Username: admin, Password: koloman, Result: " + User.authenticate("admin", "koloman"));
        System.out.println("Username: dostojev, Password: admin, Result: " + User.authenticate("dostojev", "admin"));
        System.out.println("Username: admin, Password: admin, Result: " + User.authenticate("admin", "admin"));

        System.out.println("MEALS NOW FUCKERS!!!!!!!!!!!!!!!");
        Meal newMeal1 = new Meal("Grcka hnusna", "Fakt kentus");
        Meal newMeal2 = new Meal("Vagintarian", null);

        System.out.println("Saving new users:");
        if(MealManager.getInstance().checkNameAvalilability(newMeal1.getName())){
            System.out.println(newMeal1.save());
        } else {
            System.out.println("Already present!");
        }
        if(MealManager.getInstance().checkNameAvalilability(newMeal2.getName())){
            System.out.println(newMeal2.save());
        } else {
            System.out.println("Already present!");
        }

        Meal meal = (Meal)MealManager.getInstance().find(3);
        System.out.println("Finding existing meal with id 3:");
        if(meal != null){
            System.out.println(meal.toString());
            meal.setName("Podkozak");
            meal.setDescription("Variot");
            System.out.println(meal.save());
        }

        List<Meal> all = (List<Meal>) MealManager.getInstance().getAll();

        for(Meal iter : all){
            System.out.println(iter.toString());
        }

        if(meal != null){
            meal.destroy();
        }
        
        Meal foundName = (Meal) MealManager.getInstance().find("Vagintarian");

        System.out.println("Found by name:");
        if(foundName != null){
            System.out.println(foundName.toString());
        }
        System.out.println("Number of meals: " + MealManager.getInstance().count());

        System.exit(0);
    }
}
