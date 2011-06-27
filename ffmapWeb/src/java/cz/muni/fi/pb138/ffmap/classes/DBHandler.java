package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
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

    private ClientSession master_session;
    private final String FFMAP_DATABASE = "./xml_data/ffmapDatabase.xml";
    private final String FFMAP_TEST_DATABASE = "./xml_data/ffmapTestDatabase.xml";/*
    private final String FFMAP_DATABASE = "src/java/cz/muni/fi/pb138/ffmap/xml/ffmapDatabase.xml";
    private final String FFMAP_TEST_DATABASE = "src/java/cz/muni/fi/pb138/ffmap/xml/ffmapTestDatabase.xml";*/
    private BaseXServer server;
    private final Context context;

    public static DBHandler getInstance() throws DatabaseInitException{
        if (instance == null) {
            instance = new DBHandler();
        }
        
        return instance;
    }

    /**
     * Parameterless constructor. Private in order to force the class to be
     * a singleton.
     */
    private DBHandler() throws DatabaseInitException {
        startServer();
        context = new Context();
        try {
            setMasterSession("admin", "admin");
        } catch (Exception ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            stopServer();
            throw new DatabaseInitException("Cannot establish master session!", ex);
        }
        try {
            openDatabase("ffmapDatabase");
        } catch (BaseXException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.WARNING, null, ex);
            try {
                createDatabase(FFMAP_DATABASE, "ffmapDatabase");
            } catch (Exception innerEx) {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, innerEx);
                stopServer();
                throw new DatabaseInitException("Cannot open nor create a new database!", innerEx);
            }
        } catch (IOException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            stopServer();
            throw new DatabaseInitException("Unable to initialize database!", ex);
        }
    }

    @Override @SuppressWarnings("FinalizeDeclaration")
    public void finalize() throws Throwable {
        closeMasterSession();
        stopServer();
        super.finalize();
    }

    private void setMasterSession(String userName, String password) throws IOException, BaseXException {
        master_session = new ClientSession(context, userName, password);        
    }

    private void closeMasterSession() throws IOException{
        master_session.close();
    }

    private void createDatabase(String sourceXMLLocation, String databaseName) throws BaseXException, IOException{
        master_session.execute("CREATE DATABASE " + databaseName + " " + sourceXMLLocation);
    }

    private void openDatabase(String databaseName) throws BaseXException, IOException {
        master_session.execute("OPEN " + databaseName);
    }

    public String XQueryCommand(String query) throws BaseXException, IOException{
        ClientQuery clientQuery = new ClientQuery(query , master_session);
        String result = clientQuery.execute();
        clientQuery.close();
        return result;
    }

    public void switchToTest() throws DatabaseInitException {
        try {
            master_session.execute("CLOSE");
            try {
                openDatabase("ffmapTestDatabase");
            } catch (Exception ex) {
                Logger.getLogger(DBHandler.class.getName()).log(Level.WARNING, null, ex);
                try {
                    createDatabase(FFMAP_TEST_DATABASE, "ffmapTestDatabase");
                } catch (Exception ex1) {
                    Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex1);
                    throw new DatabaseInitException("Cannot open nor create the test database!", ex1);
                }
            }
        } catch (BaseXException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void stopServer() {
        if(server != null){
            server.stop();
        }
    }

    public void createHack() {
        try {
            /*TODO: delete me */
            createDatabase(FFMAP_DATABASE, "ffmapDatabase");
        } catch (Exception ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void keyIncrement(String keyName) throws BaseXException, DatabaseInitException, IOException {
        if (!keyName.equals("joints-key-number") &&
            !keyName.equals("meals-key-number") &&
            !keyName.equals("users-key-number")) {
            throw new IllegalArgumentException("KeyName has to be either joints-key-number, meals-key-number, users-key-number");
        }

        XQueryCommand(
            "let $lastKey := /fastfood-database/@" + keyName + " " +
            "return replace value of node $lastKey with number($lastKey) + 1"
        );
    }
}
