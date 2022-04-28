package cz.muni.fi.pb162.project;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class represent piece of board game
 *
 * @author Alzbeta Strompova
 */
public class Piece implements Prototype {

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final long id;
    private final Color color;
    private PieceType pieceType;

    /**
     * Constructor takes color and type of piece and set up uniq id
     *
     * @param color     which our piece will have
     * @param pieceType which our piece will have
     */
    public Piece(Color color, PieceType pieceType) {
        id = ID_COUNTER.getAndIncrement();
        this.color = color;
        this.pieceType = pieceType;
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getTypeOfPiece() {
        return pieceType;
    }

    public void setTypeOfPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    @Override
    public String toString() {
        return String.valueOf(pieceType.name().charAt(0));
    }

    @Override
    public Piece makeClone() {
        return new Piece(color, pieceType);
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
