package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.GameBoard;
import cz.muni.fi.pb162.project.Coordinates;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Knight implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(GameBoard gameBoard, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = gameBoard.getPiece(position).getColor();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 2),
                Pair.of(-1, 2),
                Pair.of(1, -2),
                Pair.of(-1, -2)));

        for (Pair<Integer, Integer> movement : coordinates) {
            var left = position.letterNumber() + movement.getLeft();
            var right = position.number() + movement.getRight();
            if (gameBoard.getColor(left, right) == null) {
                result.add(new Coordinates(left, right));
            } else if (color.equals(gameBoard.getColor(left, right).getOppositeColor())) {
                result.add(new Coordinates(left, right));
            }
        }

        return result;
    }
}
