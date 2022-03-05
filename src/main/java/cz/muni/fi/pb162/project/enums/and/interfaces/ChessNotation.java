package cz.muni.fi.pb162.project.enums.and.interfaces;

import cz.muni.fi.pb162.project.Coordinates;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * "static" class
 * @author Alzbeta Strompova
 */
public final class ChessNotation {

    public final static Map<Pair<ChessPieces, Color>, String> figures;
    static {
        figures = new HashMap<>();
        figures.put(Pair.of(ChessPieces.King, Color.White), "\u2654");
        figures.put(Pair.of(ChessPieces.Queen, Color.White), "\u2655");
        figures.put(Pair.of(ChessPieces.Bishop, Color.White), "\u2657");
        figures.put(Pair.of(ChessPieces.Rook, Color.White), "\u2656");
        figures.put(Pair.of(ChessPieces.Knight, Color.White), "\u2658");
        figures.put(Pair.of(ChessPieces.Pawn, Color.White), "\u2659");

        figures.put(Pair.of(ChessPieces.King, Color.Black), "\u265A");
        figures.put(Pair.of(ChessPieces.Queen, Color.Black), "\u265B");
        figures.put(Pair.of(ChessPieces.Bishop, Color.Black), "\u265D");
        figures.put(Pair.of(ChessPieces.Rook, Color.Black), "\u265C");
        figures.put(Pair.of(ChessPieces.Knight, Color.Black), "\u265E");
        figures.put(Pair.of(ChessPieces.Pawn, Color.Black), "\u265F");
    }

    // due to a ban on implementation
    private ChessNotation() {
    }


    /**
     * Chess notation
     * @param x 0-7 => a-h
     * @param y 0-7 => 1-8
     * @return 04 example a5
     */
    public static String getNotationOfCoordinates(int x, int y){
        var i = (char)(97 + x);
        return i + String.valueOf(y + 1);
    }

    /**
     * Chess notation REVERSE
     * @param x a-h => 0-7
     * @param y 1-8 => 0-7
     * @return a5 example 04
     */
    public static Coordinates getCoordinatesOfNotation(char x, int y){
        var i = (int)x - 97;
        return new Coordinates(i,y - 1);
    }
}
