package cz.muni.fi.pb162.project;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract class represents the factory which creates pieces.
 * It is a part of design pattern Factory method.
 *
 * @author Alzbeta Strompova
 */
public abstract class PieceFactory implements FactoryMethodOfPiece {

    private final Map<PieceType, Piece> pieces = new EnumMap<>(PieceType.class);

    /**
     * Constructor which sets the attribute of class of type map. Map contains every PieceType which constructor gets
     * and prototype of the piece with his type and corresponding moves.
     *
     * @param setOfPieceTypes set of pieceTypes of the corresponding game.
     */
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
            throw new IllegalArgumentException("Unknown type of  piece.");
        }
        piece.setColor(color);
        return piece.makeClone();
    }

}
