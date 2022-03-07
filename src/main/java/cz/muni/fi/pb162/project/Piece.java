package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.enums.and.interfaces.TypeOfPieces;
import cz.muni.fi.pb162.project.moves.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong idCounter = new AtomicLong();
    private final static Map<TypeOfPieces, List<Move>> allowedMovesMap;
    static {
        allowedMovesMap = new HashMap<>();
        allowedMovesMap.put(TypeOfPieces.King, List.of(new Straight(1), new Diagonal(1), new Castling()));
        allowedMovesMap.put(TypeOfPieces.Queen, List.of(new Straight(), new Diagonal()));
        allowedMovesMap.put(TypeOfPieces.Bishop, List.of(new Diagonal()));
        allowedMovesMap.put(TypeOfPieces.Rook, List.of(new Straight()));
        allowedMovesMap.put(TypeOfPieces.Knight, List.of(new Knight()));
        allowedMovesMap.put(TypeOfPieces.Pawn, List.of(new Pawn()));
        allowedMovesMap.put(null, List.of());

    }

    private final static Map<Pair<TypeOfPieces, Color>, String> figures;
    static {
        figures = new HashMap<>();
        figures.put(Pair.of(TypeOfPieces.King, Color.White), "\u2654");
        figures.put(Pair.of(TypeOfPieces.Queen, Color.White), "\u2655");
        figures.put(Pair.of(TypeOfPieces.Bishop, Color.White), "\u2657");
        figures.put(Pair.of(TypeOfPieces.Rook, Color.White), "\u2656");
        figures.put(Pair.of(TypeOfPieces.Knight, Color.White), "\u2658");
        figures.put(Pair.of(TypeOfPieces.Pawn, Color.White), "\u2659");

        figures.put(Pair.of(TypeOfPieces.King, Color.Black), "\u265A");
        figures.put(Pair.of(TypeOfPieces.Queen, Color.Black), "\u265B");
        figures.put(Pair.of(TypeOfPieces.Bishop, Color.Black), "\u265D");
        figures.put(Pair.of(TypeOfPieces.Rook, Color.Black), "\u265C");
        figures.put(Pair.of(TypeOfPieces.Knight, Color.Black), "\u265E");
        figures.put(Pair.of(TypeOfPieces.Pawn, Color.Black), "\u265F");
    }

    private final long id;
    private final Color color;
    private TypeOfPieces type;

    public Piece(Color color, TypeOfPieces type) {
        id = createID();
        this.color = color;
        this.type = type;

    }

    private static long createID() {
        return idCounter.getAndIncrement();
    }

    public TypeOfPieces getType() {
        return type;
    }

    public void setType(TypeOfPieces type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public long getId() {
        return id;
    }

    public Set<Coordinates> getAllPossibleMoves(Game game) {
        var strategies = allowedMovesMap.get(type);
        var result = new HashSet<Coordinates>();
        for (Move strategy: strategies) {
            result.addAll(strategy.getAllowedMoves(game, game.findCoordinatesOfPieceById(getId())));
        }
        return result.stream().filter(game::inRange).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return figures.get(Pair.of(getType(), getColor()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass().isInstance(o.getClass())) {
            return false;
        }
        Piece piece = (Piece) o;
        return id == piece.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
