package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Castling;
import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Jump;
import cz.muni.fi.pb162.project.moves.Knight;
import cz.muni.fi.pb162.project.moves.Move;
import cz.muni.fi.pb162.project.moves.Pawn;
import cz.muni.fi.pb162.project.moves.Straight;

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
    private boolean firstMove = true;

    public boolean isFirstMove() {
        return firstMove;
    }

    public void alreadyMove() {
        firstMove = false;
    }

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

    public Set<Coordinates> getAllPossibleMoves(GameBoard gameBoard) {
        var strategies = allowedMovesMap.get(type);
        var result = new HashSet<Coordinates>();
        for (Move strategy : strategies) {
            var value = strategy.getAllowedMoves(gameBoard, gameBoard.findCoordinatesOfPieceById(getId()));
            if (value != null) {
                result.addAll(value);
            }
        }
        return result.stream()
                .filter(gameBoard::inRange)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return getType().getSymbol(getColor());
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
