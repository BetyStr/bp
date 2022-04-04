package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.Coordinates;
import java.util.Comparator;

/**
 * @author Alzbeta Strompova
 */
public class CoordinatesInverseComparator implements Comparator<Coordinates> {

    @Override
    public int compare(Coordinates o1, Coordinates o2) {
        return -1 * o1.compareTo(o2);
    }
}
