package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.enums.WeekDay;
import cz.muni.fi.pb138.ffmap.exceptions.DatabaseInitException;
import cz.muni.fi.pb138.ffmap.exceptions.MenuItemException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;

/**
 * Class representing a fast food joint.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class Joint implements IDatabaseStoreable {
    private Long id;
    private Long addedBy;
    private String name;
    private Location location;
    private LinkedHashMap<WeekDay, List<OpeningHour>> openingHours;
    private List<MenuItem> menu;
    private List<String> tags;
    private List<String> comments;
    private String webPage;

    /**
     * Load previously saved joint from the database.
     *
     * @param id inique ID of the joint
     * @param addedBy unique ID of the user who added the joint to the database
     * @param name name of the joint
     * @param location location of the joint
     * @param openingHours joint's opening hours
     * @param menu menu served by joint
     * @param tags tags that joint is tagged with
     * @param comments users' comments about the joint
     * @param webPage joint's web page
     */
    public static Joint loadJoint(Long id, Long addedBy, String name, Location location, LinkedHashMap<WeekDay, List<OpeningHour>> openingHours, List<MenuItem> menu, List<String> tags, List<String> comments, String webPage) {
        return new Joint(id, addedBy, name, location, openingHours, menu, tags, comments, webPage);
    }

    /**
     * Parametric constructor. Sets all arguments.
     *
     * @param id inique ID of the joint
     * @param addedBy unique ID of the user who added the joint to the database
     * @param name name of the joint
     * @param location location of the joint
     * @param openingHours joint's opening hours
     * @param menu menu served by joint
     * @param tags tags that joint is tagged with
     * @param comments users' comments about the joint
     * @param webPage joint's web page
     */
    private Joint(Long id, Long addedBy, String name, Location location, LinkedHashMap<WeekDay, List<OpeningHour>> openingHours, List<MenuItem> menu, List<String> tags, List<String> comments, String webPage) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID cannot be less than or equal to zero.");
        }

        if (addedBy != null && addedBy <= 0) {
            throw new IllegalArgumentException("AddedBy cannot be less than or equal to zero.");
        }

        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }

        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        if (openingHours == null) {
            throw new IllegalArgumentException("OpeningHours cannot be null");
        }

        if (menu == null) {
            throw new IllegalArgumentException("Menu cannot be null");
        }
        
        if (tags == null) {
            throw new IllegalArgumentException("Comments cannot be null");
        }

        if (comments == null) {
            throw new IllegalArgumentException("Comments cannot be null");
        }

        if (webPage == null) {
            throw new IllegalArgumentException("Webpage cannot be null");
        }

        this.id = id;
        this.addedBy = addedBy;
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
        this.menu = menu;
        this.tags = tags;
        this.comments = comments;
        this.webPage = webPage;
    }

    /**
     * Parametric constructor. This constructor is used to create new object.
     * 
     * @param addedBy unique ID of the user who added the joint to the database
     * @param name name of the joint
     * @param location location of the joint
     * @param webPage joint's web page
     */
    public Joint(Long addedBy, String name, Location location, String webPage) {
        this(null, addedBy, name, location, new LinkedHashMap<WeekDay, List<OpeningHour>>(), new ArrayList<MenuItem>(), new ArrayList<String>(), new ArrayList<String>(), webPage);
        openingHours.put(WeekDay.MON, new ArrayList<OpeningHour>());
        openingHours.put(WeekDay.TUE, new ArrayList<OpeningHour>());
        openingHours.put(WeekDay.WED, new ArrayList<OpeningHour>());
        openingHours.put(WeekDay.THU, new ArrayList<OpeningHour>());
        openingHours.put(WeekDay.FRI, new ArrayList<OpeningHour>());
        openingHours.put(WeekDay.SAT, new ArrayList<OpeningHour>());
        openingHours.put(WeekDay.SUN, new ArrayList<OpeningHour>());
    }

    public Long getId() {
        return id;
    }
    public Long getAddedBy() {
        return addedBy;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }

        this.name = name;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        this.location = location;
    }
    public List<MenuItem> getMenu() {
        return Collections.unmodifiableList(menu);
    }
    public Map<WeekDay, List<OpeningHour>> getOpeningHours() {
        return Collections.unmodifiableMap(openingHours);
    }
    public List<String> getTags() {
        return tags;
    }
    public List<String> getComments() {
        return comments;
    }
    public String getWebPage() {
        return webPage;
    }

    public boolean save() {
        if (id == null) {
            return insert();
        }

        return update();
    }
    public boolean destroy() {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Removes reference to a unique ID of the user who added this joint to the
     * database, provided there is one.
     */
    public void removeAddedBy() {
        if (addedBy != null) {
            this.addedBy = null;
        }
    }
    /**
     * Adds supplied menu item to joint's menu.
     * @param menuItem menu item to add
     */
    public void addMenuItem(MenuItem menuItem) throws MenuItemException {
        if (menuItem == null) {
            throw new IllegalArgumentException("MenuItem cannot be null");
        }

        if (menu.contains(menuItem)) {
            throw new MenuItemException("Menu already contains item " + menuItem);
        }

        menu.add(menuItem);
    }
    /**
     * Tries to remove a menu item from the menu. Provided that the passed menu
     * item is not on the menu, MenuItemException is thrown.
     *
     * @param menuItem menu item to remove from the menu
     * @throws MenuItemException
     */
    public void removeMenuItem(MenuItem menuItem) throws MenuItemException {
        if (menuItem == null) {
            throw new IllegalArgumentException("MenuItem cannot be null");
        }

        if (!menu.remove(menuItem)) {
            throw new MenuItemException("Unable to remove an item that's not on the menu.");
        }
    }
    
    public List<OpeningHour> getMonday() {
        return openingHours.get(WeekDay.MON);
    }
    
    public List<OpeningHour> getTuesday() {
        return openingHours.get(WeekDay.TUE);
    }

    public List<OpeningHour> getWednesday() {
        return openingHours.get(WeekDay.WED);
    }

    public List<OpeningHour> getThursday() {
        return openingHours.get(WeekDay.THU);
    }

    public List<OpeningHour> getFriday() {
        return openingHours.get(WeekDay.FRI);
    }

    public List<OpeningHour> getSaturday() {
        return openingHours.get(WeekDay.SAT);
    }

    public List<OpeningHour> getSunday() {
        return openingHours.get(WeekDay.SUN);
    }

    /**
     * Sets new set of opening hours.
     *
     * @param day day of the week
     * @param openingHours list of opening hours
     * @throws OpeningHourException
     */
    public void setOpeningHours(WeekDay day, List<OpeningHour> openingHours) {
        if (openingHours == null) {
            throw new IllegalArgumentException("Opening hours cannot be null");
        }

        if (openingHours.isEmpty() || openingHours.size() > 2) {
            throw new IllegalArgumentException("Invalid argument openingHours passed.");
        }

        this.openingHours.put(day, openingHours);
    }

    /**
     * Inserts new user into the database.
     *
     * @return true if user is successfully inserted, false otherwise
     */
    private boolean insert() {
        try {
            keyIncrement();

            String idQuery = "let $id := /fastfood-database/@joints-key-number " +
                             "return number($id)";

            if ((id = Long.valueOf(DBHandler.getInstance().XQueryCommand(idQuery))) == null) {
                return false;
            }

            String query = "let $joint := /fastfood-database/joints " +
                           "return insert node " +
                               "<joint id=\"" + id + "\" name=\"" + name + "\"> " +
                                   location.serialize() +
                                   serializeOpeningHours() +
                                   "<menu>";

            for (MenuItem menuItem: menu) {
                query += menuItem.serialize();
            }

            query +=               "</menu>" +
                                   "<tags>";

            for (String tag: tags) {
                query += "<tag>" + tag + "</tag>";
            }

            query +=               "</tags>" +
                                   "<comments>";

            for (String comment: comments) {
                query += "<comment>" + comment + "</comment>";
            }

            query +=               "</comments>" +
                                   "<web-page>" + webPage + "</web-page>" +
                               "</joint>" +
                           "as last into $joint";

            DBHandler.getInstance().XQueryCommand(query);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }
    /**
     * Updates previously saved user's data.
     *
     * @return true if user is successfully updated, false otherwise
     */
    private boolean update() {
        String query = "let $joint := /fastfood-database/joints/joint[@id=" + id + "]" +
                       "return (" +
                           "replace value of node $joint/@name with \"" + name + "\", " +
                           "replace node $joint/location with (" + location.serialize() + "), " +
                           "replace node $joint/opening-hours with (" + serializeOpeningHours() + ")," +
                           "replace value of node $joint/menu with (";

        for (MenuItem item: menu) {
            query += item.serialize();
        }

        query +=           "), " +
                           "replace value of node $joint/tags with (";

        for (String tag: tags) {
            query += "<tag>" + tag + "</tag>";
        }

        query +=           "), " +
                           "replace value of node $joint/comments with (";

        for (String comment: comments) {
            query += "<tag>" + comment + "</tag>";
        }

        query +=           "), " +
                           "replace value of node $joint/web-page with \"" + webPage + "\"" +
                       ")";
        try {
            DBHandler.getInstance().XQueryCommand(query);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return false;
    }

    /**
     * Increments joint key sequence by 1.
     * 
     * @throws BaseXException
     * @throws DatabaseInitException
     * @throws IOException
     */
    private void keyIncrement() throws BaseXException, DatabaseInitException, IOException {
        DBHandler.getInstance().keyIncrement("joints-key-number");
    }

    private String serializeOpeningHours() {
        String result = "<opening-hours>";

        for (WeekDay day: openingHours.keySet()) {
            result += "<" + day.toString().toLowerCase() + ">";
            for (OpeningHour openingHour: getOpeningHours().get(day)) {
                result += openingHour.serialize();
            }
            result += "</" + day.toString().toLowerCase() + ">";
        }
        
        result += "</opening-hours>";

        return result;
    }

    /** TODO: delete me */
    @Override
    public String toString() {
        return "Joint{" + "id=" + id + "addedBy=" + addedBy + "name=" + name + "location=" + location + "openingHours=" + openingHours + "menu=" + menu + "tags=" + tags + "comments=" + comments + "webPage=" + webPage + '}';
    }
}
