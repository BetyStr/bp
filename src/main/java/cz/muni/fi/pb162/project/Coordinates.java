package cz.muni.fi.pb162.project;

import java.util.Objects;

/**
 * @author Alzbeta Strompova
 */
public class Coordinates {

    private final int letterNumber;
    private final int number;

    private static final int UNICODE_VALUE_FOR_A = 97;

    // todo maybe static class
    // Chess notation
    public static char getLetterFromNumber(int number) {
        return (char) (number + UNICODE_VALUE_FOR_A);
    }

    public static int getNumberFromLetter(char letter) {
        return (int) letter - UNICODE_VALUE_FOR_A;
    }

    public Coordinates(int letterNumber, int number) {
        this.letterNumber = letterNumber;
        this.number = number;
    }

    public Coordinates(char letterNumber, int number) {
        this(getNumberFromLetter(letterNumber), number);
    }

    public int getLetterNumber() {
        return letterNumber;
    }


    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return getLetterFromNumber(letterNumber) + String.valueOf(number);
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
