package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinates;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Alzbeta Strompova
 */
public class Diagonal implements Move {

    private int step = Board.SIZE;
    private boolean onlyForward = false;

    /**
     * Constructor
     * step -> size of game board
     * onlyForward -> false
     */
    public Diagonal() {
    }

    /**
     * Constructor
     * onlyForward -> false
     *
     * @param step max distance to move
     */
    public Diagonal(int step) {
        this.step = step;
    }

    /**
     * Constructor
     *
     * @param step        max distance to m
     * @param onlyForward boolean that decide that piece can only move forward
     */
    public Diagonal(int step, boolean onlyForward) {
        this(step);
        this.onlyForward = onlyForward;
    }


    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {

        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();
        Color goal;

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 1),
                Pair.of(-1, 1)));
        if (!onlyForward) {
            coordinates.add(Pair.of(-1, -1));
            coordinates.add(Pair.of(1, -1));
        }

        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= step; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                goal = board.getColor(left, right);
                if (goal == null) {
                    result.add(new Coordinates(left, right));
                } else {
                    if (color.getOppositeColor().equals(goal)) {
                        result.add(new Coordinates(left, right));
                    }
                    break;
                }
            }
        }
        return result;
    }

}
