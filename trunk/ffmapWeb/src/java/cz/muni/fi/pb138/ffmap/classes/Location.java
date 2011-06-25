/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

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
     * @param city city's name
     * @param streetName street's name
     * @param streetNumber street's orientation number
     */
    public Location(double latitude, double longitude, String city, String streetName, String streetNumber) {
        if (city == null || city.equals("")) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }

        if (streetName == null || streetName.equals("")) {
            throw new IllegalArgumentException("StreetName cannot be null or empty");
        }

        if (streetNumber == null || streetNumber.equals("")) {
            throw new IllegalArgumentException("StreetNumber cannot be null or empty");
        }

        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getCity() {
        return city;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getStreetNumber() {
        return streetNumber;
    }

}
