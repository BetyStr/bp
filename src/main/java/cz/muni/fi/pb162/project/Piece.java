package cz.muni.fi.pb162.project;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Class represent piece
 *
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong ID_COUNTER = new AtomicLong();

    private final long id;
    private final Color color;
    private TypeOfPiece type;

    /**
     * Constructor takes color and type of piece and set up uniq id
     *
     * @param color which our piece will have
     * @param type which our piece will have
     */
    public Piece(Color color, TypeOfPiece type) {
        id = ID_COUNTER.getAndIncrement();
        this.color = color;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public TypeOfPiece getType() {
        return type;
    }

    public void setType(TypeOfPiece type) {
        this.type = type;
    }

    /**
     * Returns set of coordinates x, y which represent position at board
     * when the piece can move
     *
     * @param board representing actual layout of pieces
     * @return coordinates of all possible move at actual board
     */
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
