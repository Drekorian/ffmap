/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.Meal;
import cz.muni.fi.pb138.ffmap.classes.MealManager;
import cz.muni.fi.pb138.ffmap.classes.comparators.UserFirstnameComparator;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stash
 */
public class viewAllMealsServlet extends HttpServlet {
   
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Meal> meals = (List<Meal>) MealManager.getInstance().getAll();
            Collections.sort(meals);
            req.setAttribute("meals", meals);
            req.setAttribute("size", meals.size());
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } catch (DatabaseInitException ex) {
            Logger.getLogger(viewAllMealsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

     }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
