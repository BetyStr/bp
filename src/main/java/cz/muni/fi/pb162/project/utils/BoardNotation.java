package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.Coordinates;

/**
 * "Static" final class for manipulation with Board notation.
 * Board have coordinates 1 - {@code Board.SIZE} and A,B,C... because this is better readable for people.
 *
 * @author Alzbeta Strompova
 */
public final class BoardNotation {

    private static final int UNICODE_VALUE_FOR_A = 97;

    /**
     * Private constructor to prevent implementation.
     */
    private BoardNotation() {
    }

    private static char getLetterFromNumber(int number) {
        return (char) (number + UNICODE_VALUE_FOR_A);
    }

    private static int getNumberFromLetter(char letter) {
        return letter - UNICODE_VALUE_FOR_A;
    }

    /**
     * Method that transports coordinates to board notation.
     *
     * @param x first part of coordinates 0-7 -> a-h.
     * @param y first part of coordinates 0-7 -> 1-8.
     * @return board notation, for example 04 -> a5.
     */
    public static String getNotationOfCoordinates(int x, int y) {
        return getLetterFromNumber(x) + String.valueOf(y + 1);
    }

    /**
     * Method that transports board notation to coordinates.
     *
     * @param x first part of coordinates a-h -> 0-7.
     * @param y first part of coordinates 1-8 -> 0-7.
     * @return board notation, for example a5 -> 04.
     */
    public static Coordinates getCoordinatesOfNotation(char x, int y) {
        return new Coordinates(getNumberFromLetter(x), y - 1);
    }
}
