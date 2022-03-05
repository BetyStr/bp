package cz.muni.fi.pb162.project.moves;


import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Board;

import java.util.Set;

/**
 * Design pattern strategy
 * @author Alzbeta Strompova
 */
public interface AllowedMoves {

    /**
     * todo vracia aj suradnice mimo board ...inRange in board mozno pomoze
     * @param board is Board when we play
     * @param position position of piece
     * @return Set of allowed moves of specific piece ... subclasses
     */
    Set<Coordinates> getAllowedMoves(Board board, Coordinates position);

}
