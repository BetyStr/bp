package cz.muni.fi.pb162.project;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract class represent factory creating piece.
 *
 * @author Alzbeta Strompova
 */
public abstract class PieceFactory implements FactoryMethodOfPiece {

    private final Map<PieceType, Piece> pieces = new EnumMap<>(PieceType.class);

    protected PieceFactory(Set<PieceType> setOfPieceTypes) {
        var setOfPieces = createSetOfPrototypes(setOfPieceTypes);
        for (Piece piece : setOfPieces) {
            pieces.put(piece.getPieceType(), piece);
        }
    }

    @Override
    public abstract Set<Piece> createSetOfPrototypes(Set<PieceType> setOfPieceTypes);

    @Override
    public Piece createPiece(PieceType pieceType, Color color) {
        var piece = pieces.get(pieceType);
        if (piece == null) {
            throw new IllegalArgumentException("Unknown type");
        }
        piece.setColor(color);
        return piece.makeClone();
    }
}
