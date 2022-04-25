package cz.muni.fi.pb162.project;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public abstract class PieceFactory {

    private final Map<TypeOfPiece, Piece> pieces = new HashMap<>();

    protected PieceFactory(Set<Piece> pieces) {
        for (Piece piece : pieces) {
            this.pieces.put(piece.getTypeOfPiece(), piece);
        }
    }

    /**
     * @param typeOfPiece e
     * @param color       w
     * @return w
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
