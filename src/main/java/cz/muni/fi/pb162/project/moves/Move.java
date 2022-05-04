package cz.muni.fi.pb162.project.moves;


import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Functional interface to help implement a behavioral design pattern Strategy
 * that lets you define a family of algorithms,
 * put each of them into a separate class, and make their objects interchangeable.
 *
 * @author Alzbeta Strompova
 */
public interface Move {

    /**
     * Helper static method that return Set of Pairs Integers. These Integers representing Diagonal shifts.
     *
     * @param onlyForward boolean that decide that piece can only move forward.
     * @param color       of what piece we want to ove only forward.
     * @return set of Pairs Integers. These Integers representing Diagonal shifts.
     */
    static Set<Pair<Integer, Integer>> getDiagonalShift(boolean onlyForward, Color color) {
        Set<Pair<Integer, Integer>> coordinates = new HashSet<>();
        if (!onlyForward || !color.equals(Color.BLACK)) {
            coordinates.add(Pair.of(1, 1));
            coordinates.add(Pair.of(-1, 1));
        }
        if (!onlyForward || !color.equals(Color.WHITE)) {
            coordinates.add(Pair.of(-1, -1));
            coordinates.add(Pair.of(1, -1));
        }
        return coordinates;
    }

    /**
     * Method that returns a set of all movements for a piece at a given position
     *
     * @param game     is Game which we play, from we can get Board
     * @param position position of piece
     * @return Set of allowed moves of specific movement some piece
     */
    Set<Coordinates> getAllowedMoves(Game game, Coordinates position);

}
