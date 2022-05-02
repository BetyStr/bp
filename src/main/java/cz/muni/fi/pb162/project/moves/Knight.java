package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Game;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Part of Strategy design pattern.
 *
 * @author Alzbeta Strompova
 */
public class Knight implements Move {

    @Override
    public Set<Coordinate> getAllowedMoves(Game game, Coordinate position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinate>();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 2),
                Pair.of(1, -2),
                Pair.of(-1, 2),
                Pair.of(-1, -2),
                Pair.of(2, 1),
                Pair.of(2, -1),
                Pair.of(-2, 1),
                Pair.of(-2, -1)));

        for (Pair<Integer, Integer> movement : coordinates) {
            var left = position.letterNumber() + movement.getLeft();
            var right = position.number() + movement.getRight();
            var goalPosition = new Coordinate(left, right);
            if (Board.inRange(goalPosition) &&
                    (board.getColor(left, right) == null ||
                    board.getPiece(position).getColor().equals(board.getColor(left, right).getOppositeColor()))) {
                result.add(goalPosition);
            }
        }
        return result;
    }
}
