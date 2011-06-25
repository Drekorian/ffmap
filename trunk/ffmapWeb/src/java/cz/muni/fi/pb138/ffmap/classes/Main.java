package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import org.basex.core.BaseXException;

/**
 *
 * @author Stash
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatabaseInitException, BaseXException, IOException {
        DBHandler.getInstance().createHack(null);

        User newUser = new User("user", "password", Role.USER, "Mother", "Fucker");
        System.out.println(newUser.save());

        User user = (User)UserManager.getInstance().find(2);
        System.out.println(user);
        System.out.println("Heslo : " + user.getPassword());
        user.setFirstName("Petr");
        user.setSurname("Kobliha");
        user.save();
        System.out.println("Heslo : " + user.getPassword());

        User user2 = UserManager.getInstance().findByUserName("user");
        System.out.println(user2);

        User user3 = UserManager.getInstance().findByUserName("admin");
        System.out.println(user3);

        System.out.println(user);

        System.out.println(UserManager.getInstance().count());

        System.exit(0);
    }
}
