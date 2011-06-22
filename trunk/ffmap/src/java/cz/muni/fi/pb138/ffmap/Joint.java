package java.cz.muni.fi.pb138.ffmap;

import java.util.List;

/**
 * Class representing a fast food joint.
 *
 * @author Marek Osvald
 * @author Martin Putniorz
 * @version 2011.0622
 */

public class Joint {
    private Long id;
    private String name;
    private Long addedBy;
    private Location location;
    private List<OpeningHour> openingHours;
    private List<Meal> menu;

    /**
     * Paramentric constructor for all attributes 
     * 
     * @param name joint name
     * @param addedBy User ID
     * @param location joint location
     * @param openingHours opening hours
     * @param menu menu
     */
    public Joint(String name, Long addedBy, Location location,
            List<OpeningHour> openingHours, List<Meal> menu){
        this.id = null;
        this.name = name;
        this.addedBy = addedBy;
        this.location = location;
        this.openingHours = openingHours;
        this.menu = menu;
    }
    
    public Long getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;        
    }
    
    public Long getAddedBy(){
        return addedBy;
    }
    
    public User getAddedByUser(){
        return null;
    }
    
    public Location getLocation(){
        return location;
    }
    
    public List<OpeningHour> getOpeningHours(){
        return openingHours;
    }
    
    public List<Meal> getMenu(){
        return menu;
    }
    
    public void addMealToMenu(Meal meal){
        menu.add(meal);
    }
}
