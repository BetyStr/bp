package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.ChessNotation;

import java.util.Objects;

/**
 * @author Alzbeta Strompova
 */
public class Coordinates {

    private final int letterNumber;
    private final int number;

    public Coordinates(int letterNumber, int number) {
        this.letterNumber = letterNumber;
        this.number = number;
    }


    public int getLetterNumber() {
        return letterNumber;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return ChessNotation.getNotationOfCoordinates(letterNumber, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates point = (Coordinates) o;
        return letterNumber == point.letterNumber && number == point.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letterNumber, number);
    }
}
