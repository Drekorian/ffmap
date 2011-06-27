/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 *
 * @author Stash
 */
public class editMealServlet extends HttpServlet {

    private static final String URL = "/ffmap/editMeal/";
    private static Long _id;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editMealServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editMealServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        id = Long.valueOf(request.getRequestURI().substring(URL.length()));
        _id = id;
        try {
            Meal meal = (Meal) MealManager.getInstance().find(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
        } catch (DatabaseInitException ex) {
            Logger.getLogger(editMealServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         try {
            Meal meal = (Meal) MealManager.getInstance().find(_id);
            if(meal != null){
                String _newName = request.getParameter("mname");
                String _newDesc = request.getParameter("desc");
                if((_newName == null) || _newName.equals("")){
                    request.setAttribute("errName", "Jméno musí být vyplněno");
                    request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                    return;
                }
                if(!MealManager.getInstance().checkNameAvalilability(_newName)){
                    request.setAttribute("errName", "Tento pokrm již v databázi existuje!");
                    request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                    return;
                }
                meal.setName(_newName);
                meal.setDescription(_newDesc);
                meal.save();
                request.setAttribute("msg", "Změna byla úspěšně provedena!");
                request.getRequestDispatcher("/meals").forward(request, response);
            }
        } catch (DatabaseInitException ex) {
            Logger.getLogger(editMealServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
