/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.BaseXServer;
import org.basex.core.BaseXException;
import org.basex.server.ClientSession;
import org.basex.core.Context;
import org.basex.server.ClientQuery;

/**
 * Database connection manager.
 *
 * @author Aleksandar Zivkovic
 * @version 2011.0621
 */
public class DBHandler {
    private static DBHandler instance = null;

    private ClientSession session;
    private final String FF_MAP_XML = "src/java/cz/muni/fi/pb138/ffmap/xml/ffmapDatabaseSample.xml";
    private BaseXServer server;
    //private final Context context = new Context();

    public static DBHandler getInstance(){
        if (instance == null){
            instance = new DBHandler();

            instance.startServer();
        }

        return instance;
    }

    /*
     * Parameterless constructor. Private in order the class to be a singleton
     */
    private DBHandler() {
    }

    @Override @SuppressWarnings("FinalizeDeclaration")
    public void finalize() throws Throwable {
        stopServer();
        super.finalize();
    }

    public void setSession(String host, int port, String userName, String password) throws IOException{
        session = new ClientSession(host, port, userName, password);
    }

    public void clearSession() throws IOException{
        session.close();
    }

    public void initializeDatabase(String sourceXMLLocation) throws BaseXException, IOException{
        session.execute("CREATE DATABASE ffmap " + sourceXMLLocation);
    }

    public void initializeDatabase() throws BaseXException, IOException{
        session.execute("CREATE DATABASE ffmap " + FF_MAP_XML);
    }

    public String XQueryCommand(String query) throws BaseXException{
        return session.execute("XQUERY " + query);
    }

    public void openDatabase(String database) throws BaseXException {
        session.execute("OPEN " + database);
    }

    /**
     * TODO: Javadoc me
     */
    private void startServer(){
        if(server == null){
            server = new BaseXServer();
        }
    }
    /**
     * TODO: Javadoc me
     */
    private void stopServer(){
        if(server != null){
            server.stop();
        }
    }
}
