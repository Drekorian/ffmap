package cz.muni.fi.pb138.ffmap.classes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;

/**
 * Class representing an item on joint's menu.
 *
 * @author Marek Osvald
 * @version 2011.0624
 */
public class MenuItem {
    private Meal meal;
    private Map<OpeningHour, Double> prices;

    public static MenuItem parseMenuItem(Element menuItem) {
        try {
            Meal _meal = (Meal)MealManager.getInstance().find(Long.valueOf(menuItem.getAttribute("meal-ref")).longValue());
            Map<OpeningHour, Double> _prices = new HashMap<OpeningHour, Double>();

            for (int i = 0; i < menuItem.getElementsByTagName("price").getLength(); i++) {
                OpeningHour _openingHour = OpeningHour.parsePriceOpeningHour(((Element)menuItem.getElementsByTagName("price").item(i)));
                Double _price  = Double.valueOf(menuItem.getElementsByTagName("price").item(i).getTextContent());
                _prices.put(_openingHour, _price);
            }

            return new MenuItem(_meal, _prices);
        } catch (Exception ex) {
            Logger.getLogger(MenuItem.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return null;
    }

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

    /**
     * Serializes MenuItem to XML.
     *
     * @return serialized XML string
     */
    public String serialize() {
        String result =
            "<menu-item meal-ref=\"" + getMeal().getId() + "\"> ";
            for (OpeningHour openingHour: prices.keySet()) {
                Calendar fromCalendar = new GregorianCalendar(),
                         toCalendar = new GregorianCalendar();

                fromCalendar.setTimeInMillis(openingHour.getFrom().getTime());
                toCalendar.setTimeInMillis(openingHour.getTo().getTime());

                result += "<price from=\"" + fromCalendar.get(Calendar.HOUR) + ":" +
                                             fromCalendar.get(Calendar.MINUTE) + ":" +
                                             fromCalendar.get(Calendar.SECOND) + "\" " +
                                   "to=\"" + toCalendar.get(Calendar.HOUR) + ":" +
                                             toCalendar.get(Calendar.MINUTE) + ":" +
                                             toCalendar.get(Calendar.SECOND) + "\"" +
                          "</price>";
            }

            result += "</menu-item>";
            
        return result;
    }
}
