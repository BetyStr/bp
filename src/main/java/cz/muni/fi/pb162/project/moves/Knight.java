package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Alzbeta Strompova
 */
public class Knight implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinates>();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 2),
                Pair.of(-1, 2),
                Pair.of(1, -2),
                Pair.of(-1, -2)));

        for (Pair<Integer, Integer> movement : coordinates) {
            var left = position.letterNumber() + movement.getLeft();
            var right = position.number() + movement.getRight();
            if (board.getColor(left, right) == null ||
                    board.getPiece(position).getColor().equals(board.getColor(left, right).getOppositeColor())) {
                result.add(new Coordinates(left, right));
            }
        }
        return result;
    }
}
