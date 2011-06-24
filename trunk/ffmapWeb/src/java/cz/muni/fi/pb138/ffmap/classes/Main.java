/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import java.util.List;

/**
 *
 * @author Stash
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatabaseInitException {
        //System.out.println(JointManager.getInstance().justTesting());
        /*Meal meal = (Meal) MealManager.getInstance().find(1);
        System.out.println(meal.getName());*/

        //System.out.println(UserManager.getInstance().count());

        //System.out.println(UserManager.getInstance().find(1));


        List<?> list = UserManager.getInstance().getAll();
        System.out.println(list.get(0));
        System.out.println("Size: " +list.size());

        System.exit(0);
    }

}
