package cz.muni.fi.pb162.project;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract class represent factory creating piece.
 *
 * @author Alzbeta Strompova
 */
public abstract class PieceFactory {

    private final Map<TypeOfPiece, Piece> pieces = new EnumMap<>(TypeOfPiece.class);

    protected PieceFactory(Set<Piece> pieces) {
        for (Piece piece : pieces) {
            this.pieces.put(piece.getTypeOfPiece(), piece);
        }
    }

    /**
     * Method returns piece with type {@code typeOfPiece}, color {@code color} and unique {@code id}.
     *
     * @param typeOfPiece of new piece.
     * @param color       of new piece.
     * @return new piece with type {@code typeOfPiece}, color {@code color} and unique {@code id}.
     */
    public Piece createPiece(TypeOfPiece typeOfPiece, Color color) {
        var piece = pieces.get(typeOfPiece);
        if (piece == null) {
            throw new IllegalArgumentException("Unknown type");
        }
        piece.setColor(color);
        return piece.makeClone();
    }
}
