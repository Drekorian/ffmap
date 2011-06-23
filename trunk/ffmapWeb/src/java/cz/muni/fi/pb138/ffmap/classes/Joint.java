/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.exceptions.MealException;
import cz.muni.fi.pb138.ffmap.exceptions.OpeningHourException;
import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
    private List<OpeningHour> openingHours;
    private List<Meal> menu;

    /**
     * Parametric constructor.
     *
     * @param addedBy unique ID of the user who has added the joint to the database
     * @param name name of the joint
     * @param location location of the join
     */
    public Joint(Long addedBy, String name, Location location) {
        if (addedBy == null) {
            throw new IllegalArgumentException("AddedBy cannot be null.");
        }

        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if(this.location == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }

        this.id = null;
        this.addedBy = addedBy;
        this.name = name;
        this.location = location;
        this.openingHours = new ArrayList<OpeningHour>();
        this.menu = new ArrayList<Meal>();
    }

    public Long getID() {
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
    public List<Meal> getMenu() {
        return Collections.unmodifiableList(menu);
    }
    public List<OpeningHour> getOpeningHours() {
        return Collections.unmodifiableList(openingHours);
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
     * Adds supplied meal to joint's menu.
     * @param meal meal to add
     */
    public void addMealToMenu(Meal meal) throws MealException {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }

        if (menu.contains(meal)) {
            throw new MealException("Menu already contains meal " + meal);
        }

        menu.add(meal);
    }
    /**
     * Tries to remove a meal from the menu. Provided that the passed meal is
     * not on the menu, MealException is thrown.
     *
     * @param meal meal to remove from the menu
     * @throws MealException
     */
    public void removeMealFromMenu(Meal meal) throws MealException {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }

        if (!menu.remove(meal)) {
            throw new MealException("Unable to remove an item that's not on the menu.");
        }
    }
    /**
     * Tries to add the new set of opening hours. Provided that opening hours
     * overlapses an OpeningHourException is Thrown
     *
     * @param openingHours opening hours to add
     * @throws OpeningHourException
     */
    public void addOpeningHours(OpeningHour openingHours) throws OpeningHourException {
        if (openingHours == null) {
            throw new IllegalArgumentException("Opening hours cannot be null");
        }

        for (OpeningHour interval: this.openingHours) {
            if ((interval.getFrom().getTime() >= openingHours.getFrom().getTime() &&
                interval.getFrom().getTime() <= openingHours.getTo().getTime()) ||
                (interval.getTo().getTime() >= openingHours.getFrom().getTime() &&
                (interval.getTo().getTime() <= openingHours.getTo().getTime()))) {
                throw new OpeningHourException("Passed opening hours overlapse with current ones.");
            }
        }
    }

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
