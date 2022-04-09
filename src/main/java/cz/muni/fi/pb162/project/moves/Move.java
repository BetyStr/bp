package cz.muni.fi.pb162.project.moves;


import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;
import java.util.Set;

/**
 * Interface to help implement a behavioral design pattern
 * that lets you define a family of algorithms,
 * put each of them into a separate class, and make their objects interchangeable.
 *
 * @author Alzbeta Strompova
 */
public interface Move {

    /**
     * @param game     is Board when we play
     * @param position position of piece
     * @return Set of allowed moves of specific piece ... subclasses
     */
    Set<Coordinates> getAllowedMoves(Game game, Coordinates position);

}
