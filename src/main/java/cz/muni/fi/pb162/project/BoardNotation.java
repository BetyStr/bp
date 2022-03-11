package cz.muni.fi.pb162.project;

/**
 * "Static" final class
 *
 * @author Alzbeta Strompova
 */
public final class BoardNotation {


    private static final int UNICODE_VALUE_FOR_A = 97;

    /**
     * Private constructor to prevent implementation
     */
    private BoardNotation() {
    }

    private static char getLetterFromNumber(int number) {
        return (char) (number + UNICODE_VALUE_FOR_A);
    }

    private static int getNumberFromLetter(char letter) {
        return (int) letter - UNICODE_VALUE_FOR_A;
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
    public static Coordinates getCoordinatesOfNotation(char x, int y) {
        return new Coordinates(getNumberFromLetter(x), y - 1);
    }
}
