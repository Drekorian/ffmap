/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.Role;
import cz.muni.fi.pb138.ffmap.classes.User;
import cz.muni.fi.pb138.ffmap.classes.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stash
 */
public class registerNewUserServlet extends HttpServlet {

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
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
        //Map<String, String> messages = new HashMap<String, String>();
        String _username = request.getParameter("username");
        String _password = request.getParameter("pass");
        String _passconf = request.getParameter("passconf");
        String _firstName = request.getParameter("firstname");
        String _lastName = request.getParameter("lastname");
        Role _role = Role.USER;
        boolean valid = true;
        if((_username == null) || _username.isEmpty()){
            request.setAttribute("username_error", "Username must be filled!");
            valid = false;
        }
        if((_password == null) || _password.isEmpty()){
            request.setAttribute("pass_error", "Password must be filled!");
            valid = false;
        }
        if(!_password.equals(_passconf)){
            request.setAttribute("passconf_error", "Passwords you have entered do not match!");
            valid = false;
        }
        if((_firstName == null) || _firstName.isEmpty()){
            request.setAttribute("firstname_error", "You must fill in your first name!");
            valid = false;
        }
        if((_lastName == null) || _lastName.isEmpty()){
            request.setAttribute("lastname_error", "You must fill in your last name!");
            valid = false;
        }
        if(valid){
            if(!UserManager.getInstance().checkUsernameAvailability(_username)){
                request.setAttribute("username_error", "This username is already taken!");
                valid = false;
            }
        }
        if(valid){
            User newUser = new User(_username, User.encrypt(_password), _role, _firstName, _lastName);
            if(!newUser.save()){
                response.sendRedirect("/registration.jsp");
                return;
            }
        } else {
            dispatcher.forward(request, response);
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
