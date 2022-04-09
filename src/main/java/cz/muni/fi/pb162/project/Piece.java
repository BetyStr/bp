package cz.muni.fi.pb162.project;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Class represent piece of board game
 *
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final long id;
    private final Color color;
    private TypeOfPiece typeOfPiece;

    /**
     * Constructor takes color and type of piece and set up uniq id
     *
     * @param color       which our piece will have
     * @param typeOfPiece which our piece will have
     */
    public Piece(Color color, TypeOfPiece typeOfPiece) {
        id = ID_COUNTER.getAndIncrement();
        this.color = color;
        this.typeOfPiece = typeOfPiece;
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public TypeOfPiece getTypeOfPiece() {
        return typeOfPiece;
    }

    public void setTypeOfPiece(TypeOfPiece typeOfPiece) {
        this.typeOfPiece = typeOfPiece;
    }

    /**
     * Returns set of coordinates x, y which represent position at board
     * when the piece can move
     *
     * @param game has board which representing actual layout of pieces
     * @return coordinates of all possible move at actual board
     */
    public Set<Coordinates> getAllPossibleMoves(Game game) {
        return typeOfPiece
                .getMoves()
                .stream()
                .map(strategy -> strategy
                        .getAllowedMoves(game, game.getBoard().findCoordinatesOfPieceById(getId())))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(game.getBoard()::inRange)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return getTypeOfPiece().getSymbol(getColor());
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
