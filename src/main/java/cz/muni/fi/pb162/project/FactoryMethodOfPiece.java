package cz.muni.fi.pb162.project;

import java.util.Set;

/**
 * Interface serves as a template to implements factory method.
 * Factory Method is a creational design pattern that provides an interface for creating objects in a superclass,
 * but allows subclasses to alter the type of objects that will be created.
 *
 * @author Alzbeta Strompova
 */
public interface FactoryMethodOfPiece {

    /**
     * Method returns a set of prototypes of pieces
     * that have such types as they are in the input parameter {@code setOfPieceTypes}.
     *
     * @param setOfPieceTypes the set of types we want to be pieces.
     * @return a set of prototypes of pieces.
     * that have such types as they are in the input parameter {@code setOfPieceTypes}.
     */
    Set<Piece> createSetOfPrototypes(Set<PieceType> setOfPieceTypes);


    /**
     * Method returns the piece with type {@code typeOfPiece}, color {@code color} and unique {@code id}.
     *
     * @param pieceType of the new piece.
     * @param color     of the new piece.
     * @return new piece with type {@code typeOfPiece}, color {@code color} and unique {@code id}.
     */
    Piece createPiece(PieceType pieceType, Color color);

}
