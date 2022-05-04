package cz.muni.fi.pb162.project;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class represents the piece of the board game.
 *
 * @author Alzbeta Strompova
 */
public class Piece implements Prototype {

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final long id;
    private final Color color;
    private PieceType pieceType;

    /**
     * Constructor takes {@code color}, {@code type} and {@code moves} of piece and sets up unique {@code id}.
     *
     * @param pieceType is the type of the piece.
     * @param color     is the color of the piece.
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

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
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
