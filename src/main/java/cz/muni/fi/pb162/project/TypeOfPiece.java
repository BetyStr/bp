package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.moves.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enum represent type of piece
 *
 * @author Alzbeta Strompova
 */
public enum TypeOfPiece {
    King(List.of(new Straight(1), new Diagonal(1), new Castling())),
    Queen(List.of(new Straight(), new Diagonal())),
    Bishop(List.of(new Diagonal())),
    Rook(List.of(new Straight())),
    Knight(List.of(new Knight())),
    Pawn(List.of(new Pawn())),
    DraughtsKing(List.of(new Diagonal(1))),
    DraughtsMan(List.of(new Diagonal(1, true)));

    private final List<Move> moves;

    TypeOfPiece(List<Move> moves) {
        this.moves = moves;
    }

    public String getSymbol(Color color) {
        Map<Pair<TypeOfPiece, Color>, String> figures = new HashMap<>();
        //Chess
        figures.put(Pair.of(TypeOfPiece.King, Color.White), "\u2654");
        figures.put(Pair.of(TypeOfPiece.Queen, Color.White), "\u2655");
        figures.put(Pair.of(TypeOfPiece.Bishop, Color.White), "\u2657");
        figures.put(Pair.of(TypeOfPiece.Rook, Color.White), "\u2656");
        figures.put(Pair.of(TypeOfPiece.Knight, Color.White), "\u2658");
        figures.put(Pair.of(TypeOfPiece.Pawn, Color.White), "\u2659");

        figures.put(Pair.of(TypeOfPiece.King, Color.Black), "\u265A");
        figures.put(Pair.of(TypeOfPiece.Queen, Color.Black), "\u265B");
        figures.put(Pair.of(TypeOfPiece.Bishop, Color.Black), "\u265D");
        figures.put(Pair.of(TypeOfPiece.Rook, Color.Black), "\u265C");
        figures.put(Pair.of(TypeOfPiece.Knight, Color.Black), "\u265E");
        figures.put(Pair.of(TypeOfPiece.Pawn, Color.Black), "\u265F");

        // Draughts
        figures.put(Pair.of(TypeOfPiece.DraughtsMan, Color.White), "\u26C0");
        figures.put(Pair.of(TypeOfPiece.DraughtsKing, Color.White), "\u26C1");
        figures.put(Pair.of(TypeOfPiece.DraughtsMan, Color.Black), "\u26C2");
        figures.put(Pair.of(TypeOfPiece.DraughtsKing, Color.Black), "\u26C3");
        return figures.getOrDefault(Pair.of(this, color), " ");
    }

    public List<Move> getMoves() {
        return Collections.unmodifiableList(moves);
    }
}
