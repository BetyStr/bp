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
public record Coordinates(int letterNumber, int number) {

    @Override
    public String toString() {
        return BoardNotation.getNotationOfCoordinates(letterNumber, number);
    }

}
