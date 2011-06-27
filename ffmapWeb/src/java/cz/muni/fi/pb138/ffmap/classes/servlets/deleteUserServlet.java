/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.User;
import cz.muni.fi.pb138.ffmap.classes.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stash
 */
public class deleteUserServlet extends HttpServlet {

    private static final String URL = "/ffmap/deleteUser/";
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
            out.println("<title>Servlet deleteUserServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteUserServlet at " + request.getContextPath () + "</h1>");
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
        User _currUser = (User) request.getSession().getAttribute("logged_user");
        if (_currUser == null) {
            request.setAttribute("msg", "Ooops!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (_id == _currUser.getId()) {
            request.setAttribute("msg", "Není dovoleno smazat vlastní profil!");
            request.getRequestDispatcher("/users").forward(request, response);
            return;
        }
        User user = (User) UserManager.getInstance().find(_id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/deleteUser.jsp").forward(request, response);
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
        String _confirmation = request.getParameter("conf");
        if (_confirmation.equals("Ne")) {
            request.getRequestDispatcher("/users").forward(request, response);
            return;
        } else {
            User user;
            user = (User) UserManager.getInstance().find(_id);
            if (user != null) {
                user.destroy();
                request.setAttribute("username", user.getUserName());
            }
            request.getRequestDispatcher("/deleteUserSuccess.jsp").forward(request, response);
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
