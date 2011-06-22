package java.cz.muni.fi.pb138.ffmap;

import java.util.Date;

/**
 * Class representing fast food opening hours interval.
 *
 * @author Marek Osvald
 * @version 2011.0621
 */

class OpeningHour {
    private Date from;
    private Date to;

    public OpeningHour(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }
    public Date getTo() {
        return to;
    }
}
