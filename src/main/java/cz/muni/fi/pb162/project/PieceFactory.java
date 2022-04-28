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

    private final Map<PieceType, Piece> pieces = new EnumMap<>(PieceType.class);

    protected PieceFactory(Set<PieceType> setOfPieceTypes) {
        var setOfPieces = createSetOfPrototypes(setOfPieceTypes);
        for (Piece piece : setOfPieces) {
            pieces.put(piece.getTypeOfPiece(), piece);
        }
    }

    /**
     * Method returns a set of prototypes of pieces
     * that have such types as they are in the input parameter {@code setOfPieceTypes}.
     *
     * @param setOfPieceTypes the set of types we want to be pieces.
     * @return a set of prototypes of pieces
     * that have such types as they are in the input parameter {@code setOfPieceTypes}.
     */
    public abstract Set<Piece> createSetOfPrototypes(Set<PieceType> setOfPieceTypes);


    /**
     * Method returns piece with type {@code typeOfPiece}, color {@code color} and unique {@code id}.
     *
     * @param pieceType of new piece.
     * @param color       of new piece.
     * @return new piece with type {@code typeOfPiece}, color {@code color} and unique {@code id}.
     */
    public Piece createPiece(PieceType pieceType, Color color) {
        var piece = pieces.get(pieceType);
        if (piece == null) {
            throw new IllegalArgumentException("Unknown type");
        }
        piece.setColor(color);
        return piece.makeClone();
    }
}
