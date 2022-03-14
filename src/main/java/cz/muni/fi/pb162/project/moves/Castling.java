package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;
import java.util.Collections;
import java.util.Set;


/**
 * @author Alzbeta Strompova
 */
public class Castling implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {
        // to implement castling and en-passant we need Game (History of moves)
        return Collections.emptySet();
    }
}
