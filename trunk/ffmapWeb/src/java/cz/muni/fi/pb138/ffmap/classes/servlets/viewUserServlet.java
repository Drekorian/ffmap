package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.User;
import cz.muni.fi.pb138.ffmap.classes.UserManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for viewing user details.
 *
 * @author Marek Osvald
 * @version 2011.0624
 */
public class viewUserServlet extends HttpServlet {
    private static final String URL = "/user/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        User user;

        id = Long.valueOf(req.getRequestURI().substring(URL.length()));

        if (id == null || id <= 0) {
            //todo: handle exception
        }

        user = (User)UserManager.getInstance().find(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }

}
