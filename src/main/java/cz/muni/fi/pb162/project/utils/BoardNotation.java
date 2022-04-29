package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.Coordinate;

/**
 * "Static" final class for manipulation with Board notation.
 * Board have coordinates 1 - {@code Board.SIZE} and A,B,C... because this is better readable for people.
 *
 * @author Alzbeta Strompova
 */
public final class BoardNotation {

    private static final int UNICODE_VALUE_FOR_A = 97;

    /**
     * Private constructor to prevent implementation
     */
    private BoardNotation() {}

    private static char getLetterFromNumber(int number) {
        return (char) (number + UNICODE_VALUE_FOR_A);
    }

    private static int getNumberFromLetter(char letter) {
        return letter - UNICODE_VALUE_FOR_A;
    }

    /**
     * Board notation
     *
     * @param x 0-7 => a-h
     * @param y 0-7 => 1-8
     * @return 04 example a5
     */
    public static String getNotationOfCoordinates(int x, int y) {
        return getLetterFromNumber(x) + String.valueOf(y + 1);
    }

    /**
     * Board notation REVERSE
     *
     * @param x a-h => 0-7
     * @param y 1-8 => 0-7
     * @return a5 example 04
     */
    public static Coordinate getCoordinatesOfNotation(char x, int y) {
        return new Coordinate(getNumberFromLetter(x), y - 1);
    }
}
