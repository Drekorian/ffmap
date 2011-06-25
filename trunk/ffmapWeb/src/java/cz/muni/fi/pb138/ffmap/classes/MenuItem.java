package cz.muni.fi.pb138.ffmap.classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing an item on joint's menu.
 *
 * @author Marek Osvald
 * @version 2011.0624
 */
public class MenuItem {
    private Meal meal;
    private Map<OpeningHour, Double> prices;

    public MenuItem(Meal meal, Map<OpeningHour, Double> prices) {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }

        if (prices == null) {
            throw new IllegalArgumentException("Prices cannot be null.");
        }
        
        this.meal = meal;
        this.prices = prices;
    }

    public MenuItem(Meal meal, double price) {
        this.meal = meal;
        this.prices = new HashMap<OpeningHour, Double>();
        prices.put(new OpeningHour(new Date(OpeningHour.DAY_BEGIN), new Date(OpeningHour.DAY_END)), price);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final MenuItem other = (MenuItem)obj;

        if (this.meal != other.meal && (this.meal == null || !this.meal.equals(other.meal))) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.meal != null ? this.meal.hashCode() : 0);
        return hash;
    }

    public Meal getMeal() {
        return meal;
    }
    public Map<OpeningHour, Double> getPrices() {
        return prices;
    }

}
