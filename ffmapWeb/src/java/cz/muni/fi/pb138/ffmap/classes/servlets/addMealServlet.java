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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stash
 */
public class addMealServlet extends HttpServlet {
   
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
            out.println("<title>Servlet addMealServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addMealServlet at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("/addMeal.jsp").forward(request, response);
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
        response.setContentType("text/html");
        RequestDispatcher err = getServletContext().getRequestDispatcher("/addMeal.jsp");
        RequestDispatcher suc = getServletContext().getRequestDispatcher("/meals");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String _name = request.getParameter("mealname");
        String _desc = request.getParameter("desc");
        if((_name == null) || _name.isEmpty()){
            request.setAttribute("mealname_error", "Musíte vyplnit jméno!");
            err.forward(request, response);
            return;
        }
        try {
            if (!MealManager.getInstance().checkNameAvalilability(_name)) {
                request.setAttribute("mealname_error", "Toto jídlo již v databázi existuje!");
                err.forward(request, response);
                return;
            }
        } catch (DatabaseInitException ex) {
            Logger.getLogger(addMealServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Meal meal = new Meal(_name, _desc);
        meal.save();
        request.setAttribute("msg", "Pokrm " + _name + " úspěšně přidán!");
        suc.forward(request, response);
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
