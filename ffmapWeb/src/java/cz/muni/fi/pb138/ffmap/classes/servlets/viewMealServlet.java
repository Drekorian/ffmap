package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.Meal;
import cz.muni.fi.pb138.ffmap.classes.MealManager;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO: description
 *
 * @author Aleksandar Zivkovic
 * @version 2011.0626
 */

public class viewMealServlet extends HttpServlet {
    private static final String URL = "/ffmap/meal/";

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Long id = null;
        Meal meal;

        id = Long.valueOf(request.getRequestURI().substring(URL.length()));
        try {
            meal = (Meal) MealManager.getInstance().find(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/meal.jsp").forward(request, response);
        } catch (DatabaseInitException ex) {
            Logger.getLogger(viewMealServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
}
