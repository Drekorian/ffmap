package cz.muni.fi.pb138.ffmap.classes.servlets;

/**
 *
 * @author Marek
 */

import cz.muni.fi.pb138.ffmap.classes.JointManager;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class viewAllJointsServlet extends HttpServlet {
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<?> joints = JointManager.getInstance().getAll();
            req.setAttribute("joints", joints);
            req.setAttribute("size", joints.size());
            req.getRequestDispatcher("/joints.jsp").forward(req, resp);
        } catch (DatabaseInitException ex) {
            Logger.getLogger(viewAllMealsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
