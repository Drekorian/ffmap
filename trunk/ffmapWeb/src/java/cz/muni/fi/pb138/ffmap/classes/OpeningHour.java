package cz.muni.fi.pb138.ffmap.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.w3c.dom.Element;

/**
 * Class representing fast food opening hours interval.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

public class OpeningHour {
    public static final long DAY_BEGIN = 0;
    public static final long DAY_END   = 86399000;

    private Date from;
    private Date to;

    /**
     * Parametric constructor.
    private Date from;
    private Date to;

     *
     * @param from begin of the opening hours interval
     * @param to end of the opening hours interval
     */
    public OpeningHour(Date from, Date to) {
        if (from == null) {
            throw new IllegalArgumentException("From cannot be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("To cannot be null");
        }

        if (from.getTime() >= to.getTime()) {
            throw new IllegalArgumentException("Supplied from and to arguments does not create a time interval.");
        }

        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }
    public Date getTo() {
        return to;
    }

    public static List<OpeningHour> parseOpeningHours(Element day) {
        ArrayList<OpeningHour> result = new ArrayList<OpeningHour>();

        for (int i = 0; i < day.getElementsByTagName("open-span").getLength(); i++) {
           result.add(new OpeningHour(
               parseDate(((Element)day.getElementsByTagName("open-span").item(i)).getElementsByTagName("start").item(0).getTextContent()),
               parseDate(((Element)day.getElementsByTagName("open-span").item(i)).getElementsByTagName("end").item(0).getTextContent())
           ));
        }

        return result;
    }

    public static OpeningHour parsePriceOpeningHour(Element price) {
        return new OpeningHour(parseDate(price.getAttribute("from")),
                               parseDate(price.getAttribute("to")));
    }

    private static Date parseDate(String date) {
        String[] dateSplit = date.split(":");

        return new Date(
            Long.valueOf(dateSplit[0]).longValue() * 3600000 +
            Long.valueOf(dateSplit[1]).longValue() * 60000 +
            Long.valueOf(dateSplit[2]).longValue() * 1000 -
            3600000
        );
    }

    /**
     * Serializes the opening hours interval to XML.
     *
     * @return serialized opening hours to XML string
     */
    public String serialize() {
        Calendar fromCalendar = new GregorianCalendar(),
                 toCalendar   = new GregorianCalendar();

        fromCalendar.setTimeInMillis(from.getTime());
        toCalendar.setTimeInMillis(to.getTime());

        return "<open-span>" +
                    "<start>" + fromCalendar.get(Calendar.HOUR) + ":" +
                                fromCalendar.get(Calendar.MINUTE) + ":" +
                                fromCalendar.get(Calendar.HOUR) +
                    "</start>" +
                    "<end>" + toCalendar.get(Calendar.HOUR) + ":" +
                              toCalendar.get(Calendar.MINUTE) + ":" +
                              toCalendar.get(Calendar.HOUR) +
                    "</end>" +
                "</open-span>";
    }
}
