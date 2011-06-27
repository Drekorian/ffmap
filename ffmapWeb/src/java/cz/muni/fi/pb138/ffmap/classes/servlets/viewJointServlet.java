package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.Joint;
import cz.muni.fi.pb138.ffmap.classes.JointManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlets that displays Joint
 *
 * @author Marek Osvald
 * @version 2011.0624
 */
public class viewJointServlet extends HttpServlet {
    private static final String URL = "/ffmap/joint/";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        Joint joint;
        String javaScript = "";

        id = Long.valueOf(req.getRequestURI().substring(URL.length()));

        if (id == null || id <= 0) {
            resp.sendRedirect("/");
        }

        try {
            joint = (Joint)JointManager.getInstance().find(id);
            javaScript = "<script type=\"text/javascript\">\n"
                            + "function initialize() {\n"
                                + "var latlng = new google.maps.LatLng("
                                    + joint.getLocation().getLatitude() + ","
                                    + joint.getLocation().getLongitude() + ");\n\n"

                                + "var options = {\n"
                                    + "zoom: 11,\n"
                                    + "center: latlng,\n"
                                    + "mapTypeId: google.maps.MapTypeId.ROADMAP\n"
                                + "};\n\n"

                                + "var map = new google.maps.Map(document.getElementById(\"googleMap\"), options);\n\n"

                                + "marker = new google.maps.Marker({\n"
                                    + "map: map,\n"
                                    + "draggable: false,\n"
                                    + "position: latlng,\n"
                                    + "title: \"" + joint.getName() + "\"\n"
                                + "});\n\n"

                                + "google.maps.event.addListener(marker, 'click', function() {\n"
                                    + "infowindow.open(map, marker);\n"
                                + "});\n\n"

                                + "var infowindow = new google.maps.InfoWindow({\n"
                                    + "content: \""
                                    + "<h2>" + joint.getName() + "</h2>"
                                    + "<p>" + joint.getLocation().getStreetName() + " "
                                            + joint.getLocation().getStreetNumber() + ", "
                                            + joint.getLocation().getCity() + "</p>"
                                    + "<p><a href=\\\"" + joint.getWebPage() + "\\\">" + joint.getWebPage() + "</a>"
                                    + "\""
                                + "});\n"
                            + "}\n"
                       + "</script>";
            
            req.setAttribute("javaScript", javaScript);
            req.setAttribute("joint", joint);
            req.getRequestDispatcher("/joint.jsp").forward(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(viewAllJointsMapServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
