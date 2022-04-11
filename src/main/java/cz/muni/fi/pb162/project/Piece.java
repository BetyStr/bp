package cz.muni.fi.pb162.project;

import java.util.concurrent.atomic.AtomicLong;

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

    @Override
    public String toString() {
        return String.valueOf(getTypeOfPiece().name().charAt(0));
    }

}
