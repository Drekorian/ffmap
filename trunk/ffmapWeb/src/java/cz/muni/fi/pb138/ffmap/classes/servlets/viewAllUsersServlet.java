package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.DBHandler;
import cz.muni.fi.pb138.ffmap.enums.Role;
import cz.muni.fi.pb138.ffmap.classes.User;
import cz.muni.fi.pb138.ffmap.classes.UserManager;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.ArrayList;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<?> users = UserManager.getInstance().getAll();
        req.setAttribute("users", users);
        req.setAttribute("size", users.size());
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
