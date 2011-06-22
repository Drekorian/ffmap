package java.cz.muni.fi.pb138.ffmap;

import java.util.Date;

/**
 * Class representing a user of ffmap system.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class User {
    private Long id;
    private String userName;
    private Role role;
    private String firstName;
    private String surname;
    private Date dateRegistered;

    public User(String userName, Role role, String firstName, String surname) {
        this.userName = userName;
        this.role = role;
        this.firstName = firstName;
        this.surname = surname;
        this.dateRegistered = new Date();
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
}
