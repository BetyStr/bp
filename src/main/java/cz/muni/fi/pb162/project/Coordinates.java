package cz.muni.fi.pb162.project;

/**
 * Record representing coordinates of board
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
