package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong idCounter = new AtomicLong();
    private final static Map<TypeOfPiece, List<Move>> allowedMovesMap;

    static {
        allowedMovesMap = new HashMap<>();
        allowedMovesMap.put(TypeOfPiece.King, List.of(new Straight(1), new Diagonal(1), new Castling()));
        allowedMovesMap.put(TypeOfPiece.Queen, List.of(new Straight(), new Diagonal()));
        allowedMovesMap.put(TypeOfPiece.Bishop, List.of(new Diagonal()));
        allowedMovesMap.put(TypeOfPiece.Rook, List.of(new Straight()));
        allowedMovesMap.put(TypeOfPiece.Knight, List.of(new Knight()));
        allowedMovesMap.put(TypeOfPiece.Pawn, List.of(new Pawn()));
        allowedMovesMap.put(TypeOfPiece.DraughtsKing, List.of(new Diagonal(1), new Jump()));
        allowedMovesMap.put(TypeOfPiece.DraughtsMan, List.of(new Diagonal(1, true), new Jump(true)));

    }

    private final long id;
    private final Color color;
    private TypeOfPiece type;

    public Piece(Color color, TypeOfPiece type) {
        id = createID();
        this.color = color;
        this.type = type;

    }

    private static long createID() {
        return idCounter.getAndIncrement();
    }

    public TypeOfPiece getType() {
        return type;
    }

    public void setType(TypeOfPiece type) {
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
        for (Move strategy : strategies) {
            result.addAll(strategy.getAllowedMoves(game, game.findCoordinatesOfPieceById(getId())));
        }
        return result.stream().filter(game::inRange).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return TypeOfPiece.figures.get(Pair.of(getType(), getColor()));
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
