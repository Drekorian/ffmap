/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.classes.comparators;

import cz.muni.fi.pb138.ffmap.classes.User;
import java.util.Comparator;

/**
 *
 * @author Stash
 */
public class UserSurnameComparator implements Comparator<User>{
    public int compare(User o1, User o2) {
        return o1.getSurname().compareToIgnoreCase(o2.getSurname());
    }
}
