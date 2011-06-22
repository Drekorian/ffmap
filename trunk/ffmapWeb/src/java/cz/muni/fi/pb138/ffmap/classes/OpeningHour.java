/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes;

import java.util.Date;

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
}
