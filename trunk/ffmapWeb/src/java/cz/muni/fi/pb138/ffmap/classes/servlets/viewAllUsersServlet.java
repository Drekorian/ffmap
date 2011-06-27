package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.DBHandler;
import cz.muni.fi.pb138.ffmap.enums.Role;
import cz.muni.fi.pb138.ffmap.classes.User;
import cz.muni.fi.pb138.ffmap.classes.UserManager;
import cz.muni.fi.pb138.ffmap.classes.comparators.UserFirstnameComparator;
import cz.muni.fi.pb138.ffmap.classes.comparators.UserSurnameComparator;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author Marek
 */

public class viewAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = (List<User>) UserManager.getInstance().getAll();
        String[] _order = request.getParameterValues("order-by");
        if(_order == null){
            Collections.sort(users);
            request.setAttribute("users", users);
            request.setAttribute("size", users.size());
            request.getRequestDispatcher("/users.jsp").forward(request, response);
            return;
        }
        if(_order[0].equals("fName")){
            Collections.sort(users, new UserFirstnameComparator());
            request.setAttribute("users", users);
            request.setAttribute("size", users.size());
            request.getRequestDispatcher("/users.jsp").forward(request, response);
            return;
        }
        if(_order[0].equals("lName")) {
            Collections.sort(users, new UserSurnameComparator());
        } else {
            Collections.sort(users);
        }
        request.setAttribute("users", users);
        request.setAttribute("size", users.size());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<User> users = (List<User>) UserManager.getInstance().getAll();
        String[] _order = request.getParameterValues("order-by");
        if(_order == null){
            Collections.sort(users);
            request.setAttribute("users", users);
            request.setAttribute("size", users.size());
            request.getRequestDispatcher("/users.jsp").forward(request, response);
            return;
        }
        if(_order[0].equals("fName")){
            Collections.sort(users, new UserFirstnameComparator());
            request.setAttribute("users", users);
            request.setAttribute("size", users.size());
            request.getRequestDispatcher("/users.jsp").forward(request, response);
            return;
        }
        if(_order[0].equals("lName")) {
            Collections.sort(users, new UserSurnameComparator());
        } else {
            Collections.sort(users);
        }
        request.setAttribute("users", users);
        request.setAttribute("size", users.size());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
