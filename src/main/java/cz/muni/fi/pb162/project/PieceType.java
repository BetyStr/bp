package cz.muni.fi.pb162.project;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Enum represents the type of piece.
 *
 * @author Alzbeta Strompova
 */
public enum PieceType {
    KING,
    QUEEN,
    BISHOP,
    ROOK,
    KNIGHT,
    PAWN,
    DRAUGHTS_KING,
    DRAUGHTS_MAN;

    /**
     * Method than returns the symbol represented in Unicode based on the type and color of piece.
     * Symbols are from {@link <a href="https://www.fileformat.info/info/unicode/index.htm">www.fileformat.info</a>}.
     *
     * @param color of the piece whose symbol we want to get.
     * @return symbol represented in Unicode based on the type and color of piece.
     */
    public String getSymbol(Color color) {
        Map<Pair<PieceType, Color>, String> figures = new HashMap<>();
        //Chess
        figures.put(Pair.of(PieceType.KING, Color.WHITE), "\u2654");
        figures.put(Pair.of(PieceType.QUEEN, Color.WHITE), "\u2655");
        figures.put(Pair.of(PieceType.BISHOP, Color.WHITE), "\u2657");
        figures.put(Pair.of(PieceType.ROOK, Color.WHITE), "\u2656");
        figures.put(Pair.of(PieceType.KNIGHT, Color.WHITE), "\u2658");
        figures.put(Pair.of(PieceType.PAWN, Color.WHITE), "\u2659");

        figures.put(Pair.of(PieceType.KING, Color.BLACK), "\u265A");
        figures.put(Pair.of(PieceType.QUEEN, Color.BLACK), "\u265B");
        figures.put(Pair.of(PieceType.BISHOP, Color.BLACK), "\u265D");
        figures.put(Pair.of(PieceType.ROOK, Color.BLACK), "\u265C");
        figures.put(Pair.of(PieceType.KNIGHT, Color.BLACK), "\u265E");
        figures.put(Pair.of(PieceType.PAWN, Color.BLACK), "\u265F");

        // Draughts
        figures.put(Pair.of(PieceType.DRAUGHTS_MAN, Color.WHITE), "\u26C0");
        figures.put(Pair.of(PieceType.DRAUGHTS_KING, Color.WHITE), "\u26C1");
        figures.put(Pair.of(PieceType.DRAUGHTS_MAN, Color.BLACK), "\u26C2");
        figures.put(Pair.of(PieceType.DRAUGHTS_KING, Color.BLACK), "\u26C3");
        return figures.getOrDefault(Pair.of(this, color), " ");
    }

}
