package cz.muni.fi.pb138.ffmap.classes.servlets;

import cz.muni.fi.pb138.ffmap.classes.Joint;
import cz.muni.fi.pb138.ffmap.classes.JointManager;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for viewing all joints on a map.
 *
 * @author Marek Osvald
 * @version 2011.0627
 */

public class viewAllJointsMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String javaScript = "";
        
        try {
            List<Joint> joints = (List<Joint>)JointManager.getInstance().getAll();
            
            javaScript = "<script type=\"text/javascript\">\n"
                            + "function initialize() {\n"
                                + "var latlng = new google.maps.LatLng("
                                    + 49.19 + ","
                                    + 16.61 + ");\n\n"

                                + "var options = {\n"
                                    + "zoom: 11,\n"
                                    + "center: latlng,\n"
                                    + "mapTypeId: google.maps.MapTypeId.ROADMAP\n"
                                + "};\n\n"

                                + "var map = new google.maps.Map(document.getElementById(\"googleMap\"), options);\n\n";
            int i = 1;
            for (Joint joint: joints) {
            javaScript += "marker" + i + " = new google.maps.Marker({\n"
                        + "map: map,\n"
                        + "draggable: false,\n"
                        + "position: new google.maps.LatLng("
                            + joint.getLocation().getLatitude() + ","
                            + joint.getLocation().getLongitude() + "),\n"
                        + "title: \"" + joint.getName() + "\"\n"
                    + "});\n\n"

                    + "google.maps.event.addListener(marker" + i + ", 'click', function() {\n"
                        + "infowindow" + i + ".open(map, marker" + i + ");\n"
                    + "});\n\n"

                    + "var infowindow" + i + " = new google.maps.InfoWindow({\n"
                        + "content: \""
                        + "<h2><a href=\\\"/ffmap/joint/" + joint.getId() + "\\\">" + joint.getName() + "</a></h2>"
                        + "<p>" + joint.getLocation().getStreetName() + " "
                                + joint.getLocation().getStreetNumber() + ", "
                                + joint.getLocation().getCity() + "</p>"
                        + "<p><a href=\\\"" + joint.getWebPage() + "\\\">" + joint.getWebPage() + "</a>"
                        + "\"\n"
                    + "});\n";
                i++;
            }
            javaScript += "}\n</script>";
            req.setAttribute("javaScript", javaScript);
            req.getRequestDispatcher("/allJointsMap.jsp").forward(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(viewAllJointsMapServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
