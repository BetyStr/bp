package cz.muni.fi.pb162.project;

/**
 * Record =>
 * constructor with all attributes,
 * all attributes are final,
 * get methods but without "get" (for example getName() -> name()),
 * intelligent toString, equals, hashCode base on attributes
 *
 * @author Alzbeta Strompova
 */
public record Coordinates(int letterNumber, int number) implements Comparable<Coordinates>{

    @Override
    public String toString() {
        return BoardNotation.getNotationOfCoordinates(letterNumber, number);
    }

    @Override
    public int compareTo(Coordinates o) {
        var value = Integer.compare(letterNumber,  o.letterNumber);
        return value != 0 ? Integer.compare(number, o.number) : value;
    }
}
