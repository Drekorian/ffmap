/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import cz.muni.fi.pb138.ffmap.interfaces.IDatabaseStoreable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Class representing a meal served by fast-food restaurants.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class Meal implements IDatabaseStoreable {
    private Long id;
    private String name;
    private Map<OpeningHour, Double> prices;
    private String description;

    /**
     * Parametric constructor.
     *
     * @param name meal's name
     * @param prices prices of the meal during the day
     * @param description meal's description
     */
    public Meal(String name, Map<OpeningHour, Double> prices, String description) {
        this.id = null;
        this.name = name;
        this.prices = prices;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }
    public Map<OpeningHour, Double> getPrices() {
        return Collections.unmodifiableMap(prices);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if (description == null || description.equals("")) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }

        this.description = description;
    }

    /**
     * Sets price of the meal during the opening hours.
     *
     * @param openingHours opening hours when price is applied
     * @param price price of the meal
     */
    public void setPrice(OpeningHour openingHours, Double price) {
        if (openingHours == null) {
            throw new IllegalArgumentException("OpeningHours cannot be nully.");
        }

        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be lower or equal to zero.");
        }

        prices.put(openingHours, price);
    }
    /**
     * Sets price of the meal for the entire day.
     *
     * @param price price of the meal
     */
    public void setPrice(Double price) {
        setPrice(new OpeningHour(new Date(OpeningHour.DAY_BEGIN), new Date(OpeningHour.DAY_END)), price);
    }

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
