package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.Coordinates;
import java.util.Comparator;

/**
 * Inverse Comparator for Coordinates,
 * compares exactly the opposite as the compare method which is implemented in Coordinates.
 *
 * @author Alzbeta Strompova
 */
public class CoordinatesInverseComparator implements Comparator<Coordinates> {

    @Override
    public int compare(Coordinates o1, Coordinates o2) {
        return -1 * o1.compareTo(o2);
    }
}
