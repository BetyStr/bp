package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Castling;
import cz.muni.fi.pb162.project.moves.Move;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Class represent piece of board game
 *
 * @author Alzbeta Strompova
 */
public class Piece implements Prototype<Piece> {

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final long id;
    private final List<Move> moves;
    private Color color;
    private PieceType pieceType;

    /**
     * Constructor takes color, type and moves of piece and set up uniq id.
     *
     * @param color     which our piece will have.
     * @param pieceType which our piece will have.
     * @param moves     which our piece will have.
     */
    public Piece(Color color, PieceType pieceType, List<Move> moves) {
        id = ID_COUNTER.getAndIncrement();
        this.color = color;
        this.pieceType = pieceType;
        this.moves = moves;
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public List<Move> getMoves() {
        return Collections.unmodifiableList(moves);
    }

    /**
     * Returns set of coordinates x, y which represent position at board
     * when the piece can move.
     *
     * @param game         has board which representing actual layout of pieces
     * @param withCastling boolean decides if result contains castling moves
     * @return coordinates of all possible move at actual board
     */
    public Set<Coordinate> getAllPossibleMoves(Game game, Boolean withCastling) {
        return moves
                .stream()
                .filter(x -> withCastling || x.getClass() != Castling.class)
                .map(strategy -> strategy
                        .getAllowedMoves(game, game.getBoard().findCoordinatesOfPieceById(getId())))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Returns set of coordinates x, y which represent position at board
     * when the piece can move
     *
     * @param game has board which representing actual layout of pieces
     * @return coordinates of all possible move at actual board
     */
    public Set<Coordinate> getAllPossibleMoves(Game game) {
        return getAllPossibleMoves(game, false);
    }

    @Override
    public String toString() {
        return pieceType.getSymbol(color);
    }

    @Override
    public Piece makeClone() {
        return new Piece(color, pieceType, moves);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
