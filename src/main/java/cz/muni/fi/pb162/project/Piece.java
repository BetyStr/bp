package cz.muni.fi.pb162.project;

import java.util.Collection;
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

    public Piece(Color color, TypeOfPiece type) {
        id = createID();
        this.color = color;
        this.type = type;
    }

    private static long createID() {
        return ID_COUNTER.getAndIncrement();
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

    public Set<Coordinates> getAllPossibleMoves(Board board) {
        return type
                .getMoves()
                .stream()
                .map(strategy -> strategy
                        .getAllowedMoves(board, board.findCoordinatesOfPieceById(getId())))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(board::inRange)
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
        if (o == null || getClass() == o.getClass()) {
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
