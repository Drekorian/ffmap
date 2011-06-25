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

    /**
     * Parameterless constructor. Private in order to force the class to be
     * singleton.
     */
    private UserManager() {
    }

    public List<? extends IDatabaseStoreable> getAll() {
        List<User> result = null;
        String query = "let $users := /fastfood-database/users " +
                       "return $users";
        
        try {
            String queryResult = DBHandler.getInstance().XQueryCommand("/fastfood-database/users");
            Document document = XQueryResultController.getDocument(queryResult);
            result = new ArrayList<User>();

            for (int i = 0; i < document.getDocumentElement().getElementsByTagName("user").getLength(); i++) {
                User user = parseUser((Element)document.getDocumentElement().getElementsByTagName("user").item(i));
                if (user != null) {
                    result.add(user);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return result;
    }
    public IDatabaseStoreable find(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be less than or equal to zero.");
        }
        
        final String query = "for $user in /fastfood-database/users/user[@id=" + id + "]"
                               + "return $user";
        
        try {
            String queryResult = DBHandler.getInstance().XQueryCommand(query);

            if (queryResult == null || queryResult.equals("")) {
                return null;
            }

            Document document = XQueryResultController.getDocument(queryResult);
            return parseUser(document.getDocumentElement());
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return null;
    }
    public long count() {
        long result = -1;
        String query = "let $users := /fastfood-database/users/user " +
                       "return count($users)";

        try {
            String queryResult = DBHandler.getInstance().XQueryCommand(query);

            if (queryResult == null || queryResult.equals("")) {
                return -1;
            }

            result = Long.valueOf(queryResult);
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return result;
    }

    /**
     * Find user by his chosen login.
     * 
     * @param userName user's chosen login.
     * @return user retrieved from the database provided that one has been
     * found, false otherwise
     */
    public User findByUserName(String userName) {
        if (userName == null || userName.equals((""))) {
            throw new IllegalArgumentException("UserName cannot be null or empty.");
        }

        String query = "let $user := /fastfood-database/users/user[username=\"" + userName + "\"]" +
                       "return $user";

        try {
            String queryResult = DBHandler.getInstance().XQueryCommand(query);

            if (queryResult == null || queryResult.equals("")) {
                return null;
            }

            Document document = XQueryResultController.getDocument(queryResult);
            return parseUser(document.getDocumentElement());
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return null;
    }
    /**
     * Converts String to Role.
     *
     * @param role string to convert
     * @return value of the string converted to Role
     */
    private Role parseRole(String role) {
        if (role == null || role.equals("")) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }

        if (role.equals("admin")) {
            return Role.ADMIN;
        }

        if (role.equals("user")) {
            return Role.USER;
        }

        throw new IllegalArgumentException("Passed argument cannot be converted to Role.");
    }
    /**
     * Converts String to a Date.
     *
     * @param date String to convert
     * @return value of the string converted to Date
     */
    private Date parseDate(String date) {
        if (date == null || date.equals("")) {
            throw new IllegalArgumentException("Date cannot be null or empty.");
        }
        
        String[] dateParts = date.split("-");

        if (dateParts.length != 3) {
            throw new IllegalArgumentException("Passed argument cannot be converted to Date.");
        }

        return new Date(
            new GregorianCalendar(Integer.parseInt(dateParts[0]),
                                  Integer.parseInt(dateParts[1]),
                                  Integer.parseInt(dateParts[2])
            ).getTimeInMillis()
        );
    }
    /**
     * Converts String to a boolean.
     *
     * @param bool String to convert
     * @return String value converted to boolean
     */
    private boolean parseBoolean(String bool) {
        if (bool == null || bool.equals("")) {
            throw new IllegalArgumentException("Bool cannot be null or empty.");
        }

        if (bool.equals("true")) {
            return true;
        }

        return false;
    }
    /**
     * Parses data from the User Element and recrates a User from the database.
     *
     * @param user Element holding the user's data
     * @return User recreated from the database if parsing is successful,
     * null otherwise
     */
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

            result = User.loadUser(_id, _username, _password, _role, _firstName, _surname, _dateRegistered, _active);
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
