package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseManager;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class managing data retrieval of the Joint class.
 *
 * @author Marek Osvald
 * @version 2011.0624
 */

public class UserManager implements IDatabaseManager {
    private static UserManager instance = null;

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    private UserManager() {
    }

    public List<? extends IDatabaseStoreable> getAll() {
        List<User> result = new ArrayList<User>();

        try {
            Document document = XQueryResultController.getDocument(DBHandler.getInstance().XQueryCommand("/fastfood-database/users"));

            for (int i = 0; i < document.getDocumentElement().getElementsByTagName("user").getLength(); i++) {
                User user = parseUser((Element)document.getDocumentElement().getElementsByTagName("user").item(i));
                if (user != null) {
                    result.add(user);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public IDatabaseStoreable find(long id) {
        try {
            Document document = XQueryResultController.getDocument(DBHandler.getInstance().XQueryCommand("/fastfood-database/users/user[@id=" + id + "]"));
            return parseUser(document.getDocumentElement());
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public long count() {
        long result = -1;

        try {
            Document document = XQueryResultController.getDocument(DBHandler.getInstance().XQueryCommand("/fastfood-database/users"));
            result = document.getDocumentElement().getElementsByTagName("user").getLength();
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    private Role parseRole(String role) {
        if (role == null || role.equals("")) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }

        if (role.equals("admin")) {
            return Role.ADMIN;
        }

        return Role.USER;
    }

    private Date parseDate(String date) {
        String[] dateParts = date.split("-");

        if (date == null || date.equals("")) {
            throw new IllegalArgumentException("Date cannot be null or empty.");
        }

        return new Date(
            new GregorianCalendar(Integer.parseInt(dateParts[0]),
                                  Integer.parseInt(dateParts[1]),
                                  Integer.parseInt(dateParts[2])
            ).getTimeInMillis()
        );
    }

    private boolean parseBoolean(String bool) {
        if (bool == null || bool.equals("")) {
            throw new IllegalArgumentException("Bool cannot be null or empty.");
        }

        if (bool.equals("true")) {
            return true;
        }

        return false;
    }

    private User parseUser(Element user) {
        User result = null;

        try {
            Long _id = Long.valueOf(user.getAttribute("id"));
            String _username = user.getElementsByTagName("username").item(0).getTextContent();
            String _password = user.getElementsByTagName("password").item(0).getTextContent();
            Role _role = parseRole(user.getElementsByTagName("role").item(0).getTextContent());
            String _firstName = user.getElementsByTagName("first-name").item(0).getTextContent();
            String _surname = user.getElementsByTagName("surname").item(0).getTextContent();
            Date _dateRegistered = parseDate(user.getElementsByTagName("date-registered").item(0).getTextContent());
            boolean _active = parseBoolean(user.getElementsByTagName("active").item(0).getTextContent());

            result = User.loadUser(_id, _username, _role, _firstName, _surname, _dateRegistered, _active);
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
