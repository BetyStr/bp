package cz.muni.fi.pb162.project.enums.and.interfaces;

import cz.muni.fi.pb162.project.Coordinates;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * "static" final class
 * @author Alzbeta Strompova
 */
public final class ChessNotation {

    public final static Map<Pair<TypeOfChessPieces, Color>, String> figures;
    static {
        figures = new HashMap<>();
        figures.put(Pair.of(TypeOfChessPieces.King, Color.White), "\u2654");
        figures.put(Pair.of(TypeOfChessPieces.Queen, Color.White), "\u2655");
        figures.put(Pair.of(TypeOfChessPieces.Bishop, Color.White), "\u2657");
        figures.put(Pair.of(TypeOfChessPieces.Rook, Color.White), "\u2656");
        figures.put(Pair.of(TypeOfChessPieces.Knight, Color.White), "\u2658");
        figures.put(Pair.of(TypeOfChessPieces.Pawn, Color.White), "\u2659");

        figures.put(Pair.of(TypeOfChessPieces.King, Color.Black), "\u265A");
        figures.put(Pair.of(TypeOfChessPieces.Queen, Color.Black), "\u265B");
        figures.put(Pair.of(TypeOfChessPieces.Bishop, Color.Black), "\u265D");
        figures.put(Pair.of(TypeOfChessPieces.Rook, Color.Black), "\u265C");
        figures.put(Pair.of(TypeOfChessPieces.Knight, Color.Black), "\u265E");
        figures.put(Pair.of(TypeOfChessPieces.Pawn, Color.Black), "\u265F");
    }

    private static final int UNICODE_VALUE_FOR_A = 97;

    /**
     * Private constructor
     * to prevent implementation
     */
    private ChessNotation() {
    }

    private static char getLetterFromNumber(int number) {
        return (char) (number + UNICODE_VALUE_FOR_A);
    }

    private static int getNumberFromLetter(char letter) {
        return (int) letter - UNICODE_VALUE_FOR_A;
    }

    /**
     * Chess notation
     * @param x 0-7 => a-h
     * @param y 0-7 => 1-8
     * @return 04 example a5
     */
    public static String getNotationOfCoordinates(int x, int y){
        return getLetterFromNumber(x) + String.valueOf(y + 1);
    }

    /**
     * Chess notation REVERSE
     * @param x a-h => 0-7
     * @param y 1-8 => 0-7
     * @return a5 example 04
     */
    public static Coordinates getCoordinatesOfNotation(char x, int y){
        return new Coordinates(getNumberFromLetter(x),y - 1);
    }
}
