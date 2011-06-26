package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.enums.WeekDay;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseManager;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class managing data retrieval of the Joint class.
 *
 * @author Marek Osvald
 * @version 2011.0622
 */
public class JointManager implements IDatabaseManager {
    private static JointManager instance = null;
    private DBHandler database;

    public static JointManager getInstance() throws DatabaseInitException {
        if (instance == null) {
            instance = new JointManager();
        }

        return instance;
    }

    private JointManager() throws DatabaseInitException {
        database = DBHandler.getInstance();
    }

    public List<? extends IDatabaseStoreable> getAll() {
        List<Joint> result = null;
        String query = "let $joints := /fastfood-database/joints " +
                       "return $joints";

        try {
            String queryResult = DBHandler.getInstance().XQueryCommand(query);
            Document document = XQueryResultController.getDocument(queryResult);
            result = new ArrayList<Joint>();

            for (int i = 0; i < document.getDocumentElement().getElementsByTagName("joint").getLength(); i++) {
                Joint joint = parseJoint((Element)document.getDocumentElement().getElementsByTagName("joint").item(i));
                if (joint != null) {
                    result.add(joint);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return result;
    }
    public IDatabaseStoreable find(long id) {
        String query = "let $joint := /fastfood-database/joints/joint[@id=" + id + "]" +
                       "return $joint";
        try {
            String queryResult = DBHandler.getInstance().XQueryCommand(query);

            if (queryResult == null || queryResult.equals("")) {
                return null;
            }
            
            Document document = XQueryResultController.getDocument(queryResult);
            return parseJoint(document.getDocumentElement());
        } catch (Exception ex) {
            Logger.getLogger(JointManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return null;
    }
    public long count() {
        long result = -1;
        String query = "let $joint := /fastfood-database/joints/joint " +
                       "return count($joint)";

        try {
            String queryResult = DBHandler.getInstance().XQueryCommand(query);

            if (queryResult == null || queryResult.equals("")) {
                return -1;
            }

            result = Long.valueOf(queryResult);
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return result;
    }

    private Joint parseJoint(Element joint) {
        Joint result = null;

        Long _id = Long.valueOf(joint.getAttribute("id"));
        Long _addedBy = Long.valueOf(joint.getAttribute("added-by"));
        String _name = joint.getAttribute("name");
        Location location = Location.parseLocation((Element)joint.getElementsByTagName("location").item(0));

        LinkedHashMap<WeekDay, List<OpeningHour>> _openingHours = new LinkedHashMap<WeekDay, List<OpeningHour>>();
        _openingHours.put(WeekDay.MON, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("mon").item(0)));
        _openingHours.put(WeekDay.TUE, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("tue").item(0)));
        _openingHours.put(WeekDay.WED, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("wed").item(0)));
        _openingHours.put(WeekDay.THU, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("thu").item(0)));
        _openingHours.put(WeekDay.FRI, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("fri").item(0)));
        _openingHours.put(WeekDay.SAT, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("sat").item(0)));
        _openingHours.put(WeekDay.SUN, OpeningHour.parseOpeningHours((Element)joint.getElementsByTagName("sun").item(0)));

        List<MenuItem> _menu = new ArrayList<MenuItem>();
        for (int i = 0; i < joint.getElementsByTagName("menu-item").getLength(); i++) {
            _menu.add(MenuItem.parseMenuItem((Element)joint.getElementsByTagName("menu-item").item(i)));
        }

        List<String> _tags = new ArrayList<String>();
        for (int i = 0; i < joint.getElementsByTagName("tag").getLength(); i++) {
            _tags.add(joint.getElementsByTagName("tag").item(i).getTextContent());
        }
        
        List<String> _comments = new ArrayList<String>();
        for (int i = 0; i < joint.getElementsByTagName("comment").getLength(); i++) {
            _comments.add(joint.getElementsByTagName("comment").item(i).getTextContent());
        }

        String _webPage = joint.getElementsByTagName("web-page").item(0).getTextContent();

        result = Joint.loadJoint(_id, _addedBy, _name, location, _openingHours, _menu, _tags, _comments, _webPage);
        
        return result;
    }
}
