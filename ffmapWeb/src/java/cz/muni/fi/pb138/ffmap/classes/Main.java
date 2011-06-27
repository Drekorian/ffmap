package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
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
        DBHandler.getInstance().createHack();

        for (int i = 0; i < JointManager.getInstance().getCheapest().size(); i++) {
            System.out.println(JointManager.getInstance().getCheapest().get(i));
        }

        System.exit(0);
    }
}
