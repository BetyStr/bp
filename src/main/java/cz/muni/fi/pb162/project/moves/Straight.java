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
public class Straight implements Move {

    private int step = GameBoard.SIZE;

    public Straight() {
    }

    public Straight(int step) {
        this.step = step;
    }

    @Override
    public Set<Coordinates> getAllowedMoves(GameBoard gameBoard, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = gameBoard.getPiece(position).getColor();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(0, 1),
                Pair.of(0, -1),
                Pair.of(1, 0),
                Pair.of(-1, 0)));

        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= step; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                if (gameBoard.getColor(left, right) == null) {
                    result.add(new Coordinates(left, right));
                } else if (color.getOppositeColor().equals(gameBoard.getColor(left, right))) {
                    result.add(new Coordinates(left, right));
                    break;
                } else {
                    break;
                }
            }
        }
        return result;
    }

}
