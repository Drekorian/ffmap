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

        System.exit(0);
    }
}
