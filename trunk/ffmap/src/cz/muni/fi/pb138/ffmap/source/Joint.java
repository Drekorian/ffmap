package cz.muni.fi.pb138.ffmap.source;

import java.util.List;

/**
 * Class representing a fast food joint.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class Joint {
    private Long id;
    private String name;
    private Long addedBy;
    private Location location;
    private List<OpeningHour> openingHours;
    private List<Meal> menu;


}
