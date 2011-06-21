/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.source;

import org.basex.BaseXServer;
import org.basex.core.BaseXException;
import org.basex.server.ClientSession;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;

/**
 *
 * @author Stash
 */
public class Main {

    public static void main(final String[] args) throws Exception {

        DBHandler handler = DBHandler.getInstance();
        
        handler.startServer();
        handler.setSession("localhost", 1984, "admin", "admin");
        handler.initializeDatabase();

        System.out.println(handler.XQueryCommand("doc('ffmapDatabase')//users"));
        handler.stopServer();
    }
  

}
