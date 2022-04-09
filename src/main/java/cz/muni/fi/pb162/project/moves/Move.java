package cz.muni.fi.pb162.project.moves;


import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;
import java.util.Arrays;
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
     * Method that returns a set of all movements for a piece at a given position
     *
     * @param game     is Game which we play, from we can get Board
     * @param position position of piece
     * @return Set of allowed moves of specific movement some piece
     */
    Set<Coordinates> getAllowedMoves(Game game, Coordinates position);

    /**
     * Helper static method that return Set of Pairs Integers. These Integers representing Diagonal shifts.
     *
     * @param onlyForward boolean that decide that piece can only move forward
     * @return set of Pairs Integers. These Integers representing Diagonal shifts.
     */
    static Set<Pair<Integer, Integer>> getDiagonalShift(boolean onlyForward) {
        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 1),
                Pair.of(-1, 1)));
        if (!onlyForward) {
            coordinates.add(Pair.of(-1, -1));
            coordinates.add(Pair.of(1, -1));
        }
        return coordinates;
    }

}
