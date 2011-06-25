package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;

/**
 * Class representing a user of ffmap system.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class User implements IDatabaseStoreable {
    private Long id;
    private String userName;
    private String password;
    private Role role;
    private String firstName;
    private String surname;
    private Date dateRegistered;
    private boolean active;

    /**
     * Recreates user previously saved to the databse.
     * 
     * @param id unique ID of the user
     * @param userName user's chosen login
     * @param role role of the user
     * @param firstName user's first name
     * @param surname user's surname
     * @param dateRegistered date of user's registration
     * @param active whether user is active or not
     * @return user recreated from the database
     */
    public static User loadUser(Long id, String userName, String password, Role role, String firstName, String surname, Date dateRegistered, boolean active) {
        return new User(id, userName, password, role, firstName, surname, dateRegistered, active);
    }

    /**
     * Primate constructor. Sets all attributes.
     * 
     * @param id unique ID of the user
     * @param userName user's chosen login
     * @param role role of the user
     * @param firstName user's first name
     * @param surname user's surname
     * @param dateRegistered date of user's registration
     * @param active whether user is active or not
     */
    private User(Long id, String userName, String password, Role role, String firstName, String surname, Date dateRegistered, boolean active) {
        this.id = id;
        this.userName = userName;
        this.password = encrypt(password);
        this.role = role;
        this.firstName = firstName;
        this.surname = surname;
        this.dateRegistered = dateRegistered;
        this.active = active;
    }
    /**
     * Parametric constructor.
     *
     * @param userName user's name to log into the system
     * @param role role of the user in the system
     * @param firstName user's first name
     * @param surname user's surname
     */
    public User(String userName, String password, Role role, String firstName, String surname) {
        this(null, userName, password, role, firstName, surname, new Date(), true);
    }

    public boolean save() {
        if (id == null) {
            return insert();
        } else {
            return update();
        }
    }
    public boolean destroy() {
        if (id == null) {
            return false;
        }

        try {
            DBHandler.getInstance().XQueryCommand("for $user in /fastfood-database/users/user[@id=" + id +"]"
                                                + "return delete node $user");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Date getDateRegistered() {
        return dateRegistered;
    }
    public boolean getActive() {
        return active;
    }

    /**
     * Alias for getActive().
     * 
     * @return true when user is active, false otherwise.
     */
    public boolean isActive() {
        return getActive();
    }
    /**
     * Activates user provided that user is inactive. Otherwise does nothing.
     */
    public void activate() {
        if (!active) {
            active = true;
        }
    }
    /**
     * Deactivates the user provided that user is active. Otherwise does
     * nothing.
     */
    public void deactivate() {
        if (active) {
            active = false;
        }
    }

    @Override
    public String toString() {
        //TODO: delete me
        return userName + " [" + id + "]: " + role + ", " + firstName + " " + surname + ", " + dateRegistered + ", " + active;
    }

    private boolean insert() {
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(dateRegistered.getTime());
            int day = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            keyIncrement();
            id = Long.valueOf(DBHandler.getInstance().XQueryCommand(
                "let $id := /fastfood-database/@users-key-number " +
                "return number($id)"));
            
            DBHandler.getInstance().XQueryCommand(
                "let $users := /fastfood-database/users " +
                "return insert node" +
                    "<user id=\"" + id + "\">" +
                        "<username>" + userName + "</username> " +
                        "<password>" + password + "</password>" +
                        "<role>" + role.toString().toLowerCase() + "</role>" +
                        "<first-name>" + firstName + "</first-name>" +
                        "<surname>" + surname + "</surname>" +
                        "<date-registered>" + year + "-" + month + "-" + day + "</date-registered>" +
                        "<active>" + (active ? "true" : "false") + "</active>" +
                    "</user>"+
                "as last into $users"
            );

            return true;
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    private boolean update() {
        throw new UnsupportedOperationException();
    }

    private void keyIncrement() throws BaseXException, DatabaseInitException, IOException {
        DBHandler.getInstance().keyIncrement("users-key-number");
    }

    private String encrypt(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            digest.update(input.getBytes());
            return digest.digest().toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("SHA1 algorithm not supported.");
        }
    }
}
