package cz.muni.fi.pb162.project.moves;


import cz.muni.fi.pb162.project.GameBoard;
import cz.muni.fi.pb162.project.Coordinates;

import java.util.Set;

/**
 * Design pattern strategy
 *
 * @author Alzbeta Strompova
 */
public interface Move {

    /**
     * @param gameBoard     is Board when we play
     * @param position position of piece
     * @return Set of allowed moves of specific piece ... subclasses
     */
    Set<Coordinates> getAllowedMoves(GameBoard gameBoard, Coordinates position);

}
