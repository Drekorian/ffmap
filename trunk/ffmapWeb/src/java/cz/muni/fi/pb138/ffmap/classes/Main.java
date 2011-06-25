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

        for (IDatabaseStoreable user: UserManager.getInstance().getAll()) {
            System.out.println((User)user);
        }
        System.exit(0);
    }
}
