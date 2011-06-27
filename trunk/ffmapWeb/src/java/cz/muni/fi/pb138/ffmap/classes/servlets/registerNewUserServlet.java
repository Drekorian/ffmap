/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.enums.Role;
import cz.muni.fi.pb138.ffmap.classes.User;
import cz.muni.fi.pb138.ffmap.classes.UserManager;
import java.io.IOException;
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
        RequestDispatcher err = getServletContext().getRequestDispatcher("/registration.jsp");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String _username = request.getParameter("username");
        String _password = request.getParameter("pass");
        String _passconf = request.getParameter("passconf");
        String _firstName = request.getParameter("firstname");
        String _lastName = request.getParameter("lastname");
        Role _role = Role.USER;
        boolean valid = true;
        if((_username == null) || _username.isEmpty()){
            request.setAttribute("username_error", "Zvolte si uživatelské jméno!");
            valid = false;
        } else {
            request.setAttribute("uname", _username);
        }
        if((_password == null) || _password.isEmpty()){
            request.setAttribute("pass_error", "Musíte vyplnit heslo!");
            valid = false;
        } else {
            if(_password.length() < 6){
                request.setAttribute("pass_error", "Heslo musí mít alespoň 6 znaků!");
                valid = false;
            }
        }
        if(!_password.equals(_passconf)){
            request.setAttribute("passconf_error", "Hesla se neshodují!");
            valid = false;
        }
        if((_firstName == null) || _firstName.isEmpty()){
            request.setAttribute("firstname_error", "Musíte vyplnit své křestní jméno!");
            valid = false;
        } else {
            request.setAttribute("fname", _firstName);
        }
        if((_lastName == null) || _lastName.isEmpty()){
            request.setAttribute("lastname_error", "Musíte vyplnit své příjmení!");
            valid = false;
        } else {
            request.setAttribute("lname", _lastName);
        }
        if(valid){
            if(!UserManager.getInstance().checkUsernameAvailability(_username)){
                request.setAttribute("username_error", "Toto uživatelské jméno je již obsazeno!");
                valid = false;
            }
        }
        if(valid){
            User newUser = new User(_username, _password, _role, _firstName, _lastName);
            if(!newUser.save()){
                err.forward(request, response);
            } else {
                RequestDispatcher suc = getServletContext().getRequestDispatcher("/registrationSuccess.jsp");
                request.setAttribute("username", _username);
                request.setAttribute("password", _password);
                request.setAttribute("firstname", _firstName);
                request.setAttribute("lastname", _lastName);
                suc.forward(request, response);
            }
        } else {
            err.forward(request, response);
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
