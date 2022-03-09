package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Move;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong ID_COUNTER = new AtomicLong();

    private final long id;
    private final Color color;
    private TypeOfPiece type;
    private boolean firstMove = true;

    public Piece(Color color, TypeOfPiece type) {
        id = createID();
        this.color = color;
        this.type = type;

    }

    private static long createID() {
        return ID_COUNTER.getAndIncrement();
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void alreadyMove() {
        firstMove = false;
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
        var strategies = type.getMoves();
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
