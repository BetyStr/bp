package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Alzbeta Strompova
 */
public class Straight implements Move {

    private int step = Board.SIZE;

    public Straight() {
    }

    public Straight(int step) {
        this.step = step;
    }

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(0, 1),
                Pair.of(0, -1),
                Pair.of(1, 0),
                Pair.of(-1, 0)));

        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= step; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                if (board.getColor(left, right) == null) {
                    result.add(new Coordinates(left, right));
                } else {
                    if (color.getOppositeColor().equals(board.getColor(left, right))) {
                        result.add(new Coordinates(left, right));
                    }
                    break;
                }
            }
        }
        return result;
    }

}
