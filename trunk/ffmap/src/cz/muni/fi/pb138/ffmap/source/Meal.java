package cz.muni.fi.pb138.ffmap.source;

import java.util.Currency;
import java.util.Locale;
import java.util.Map;

/**
 * Class representing a meal served by fast-food restaurants.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

class Meal {
    private Long id;
    private String name;
    private Map<OpeningHour, Double> prices;
    private String description;

    public Meal(String name, Map<OpeningHour, Double> prices, String description) {
        this.id = null;
        this.name = name;
        this.prices = prices;
        this.description = description;
    }

    public Long getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<OpeningHour, Double> getPrices() {
        return prices;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(OpeningHour openingHours, Double price) {
        prices.put(openingHours, price);
    }
}
