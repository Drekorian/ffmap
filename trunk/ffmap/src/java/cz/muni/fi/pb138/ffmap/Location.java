package java.cz.muni.fi.pb138.ffmap;

/**
 * Class representing location of a fast food joint.
 *
 * @author Marek Osvald
 * @author Martin Putniorz
 * @version 2011.0622
 */

public class Location {
    private double latitude;
    private double longitude;
    private String city;
    private String streetName;
    private String streetNumber;

    /**
     * Parametric constructor. Sets all attributes.
     *
     * @param latitude geographical latitude
     * @param longitude geographical longitude
     * @param city name of the city
     * @param streetName name of the street
     * @param streetNumber street number
     */
    public Location(double latitude, double longitude, String city, String streetName, String streetNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }
    
    public double getLatitude(){
        return latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getStreetName(){
        return streetName;
    }
    
    public String getStreetNumber(){
        return streetNumber;
    }
}
