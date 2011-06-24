/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.util.Date;

/**
 * Class representing a user of ffmap system.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class User implements IDatabaseStoreable {
    private Long id;
    private String userName;
    private Role role;
    private String firstName;
    private String surname;
    private Date dateRegistered;
    private boolean active;

    public static User loadUser(Long id, String userName, Role role, String firstName, String surname, Date dateRegistered, boolean active) {
        return new User(id, userName, role, firstName, surname, dateRegistered, active);
    }

    private User(Long id, String userName, Role role, String firstName, String surname, Date dateRegistered, boolean active) {
        this.id = id;
        this.userName = userName;
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
    public User(String userName, Role role, String firstName, String surname) {
        this(null, userName, role, firstName, surname, new Date(), true);
    }

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getID() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isActive() {
        return getActive();
    }

    public void activate() {
        if (!active) {
            active = true;
        }
    }

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
}
