/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.source;

import java.io.IOException;
import org.basex.BaseXServer;
import org.basex.core.BaseXException;
import org.basex.server.ClientSession;
import org.basex.core.Context;
import org.basex.server.ClientQuery;

/**
 *
 * @author Stash
 */
public class DBHandler {

    private static DBHandler instance = null;

    private ClientSession session;
    private final String FF_MAP_XML = "./src/cz/muni/fi/pb138/ffmap/xml/ffmapDatabaseSample.xml";
    private BaseXServer server;
    //private final Context context = new Context();

    private DBHandler() {}

    public static DBHandler getInstance(){
        if(instance == null){
            instance = new DBHandler();
        }
        return instance;
    }

    public void startServer(){
        if(server == null){
            server = new BaseXServer();
        }
    }

    public void stopServer(){
        if(server != null){
            server.stop();
        }
    }

    public void setSession(String host, int port, String userName, String password) throws IOException{
        session = new ClientSession(host, port, userName, password);
    }

    public void clearSession() throws IOException{
        session.close();
    }

    public void initializeDatabase(String sourceXMLLocation) throws BaseXException, IOException{
        session.execute("CREATE DATABASE ffmapDatabase " + sourceXMLLocation);
    }

    public void initializeDatabase() throws BaseXException, IOException{
        session.execute("CREATE DATABASE ffmapDatabase " + FF_MAP_XML);
    }

    public String XQueryCommand(String query) throws BaseXException{
        return session.execute("XQUERY " + query);
    }

}
