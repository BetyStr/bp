package cz.muni.fi.pb162.project;


/**
 * @author Alzbeta Strompova
 */
public record Coordinates(int letterNumber, int number) {

    @Override
    public String toString() {
        return ChessNotation.getNotationOfCoordinates(letterNumber, number);
    }

}
