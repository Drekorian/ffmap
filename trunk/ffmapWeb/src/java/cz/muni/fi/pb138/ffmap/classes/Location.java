/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import org.w3c.dom.Element;

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
     * TODO: javadoc
     * @param location
     * @return
     */
    public static Location parseLocation(Element location) {
        double _latitude = Double.valueOf(location.getAttribute("lat")).doubleValue();
        double _longitude = Double.valueOf(location.getAttribute("lon")).doubleValue();
        String _city = ((Element)location.getElementsByTagName("adress").item(0)).getElementsByTagName("city").item(0).getTextContent();
        String _streeName = ((Element)location.getElementsByTagName("adress").item(0)).getElementsByTagName("street-name").item(0).getTextContent();
        String _streeNumber = ((Element)location.getElementsByTagName("adress").item(0)).getElementsByTagName("street-number").item(0).getTextContent();

        return new Location(_latitude, _longitude, _city, _streeName, _streeNumber);
    }

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

    /**
     * Serializes the Location to XML.
     *
     * @return serialized XML string
     */
    public String serialize() {
        String result = "<location lat=\"" + latitude + "\" lon=\"" + longitude + "\"> " +
                           "<address> " +
                                "<city>" + city + "</city>" +
                                "<street-name>" + streetName + "</street-name> " +
                                "<street-number>" + streetNumber + "</street-number> " +
                            "</address>" +
                        "</location> ";

        return result;
    }
}
