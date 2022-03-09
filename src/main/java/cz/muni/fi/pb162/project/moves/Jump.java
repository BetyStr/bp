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
public class Jump implements Move {

    private boolean onlyForward = false;

    public Jump() {
    }

    /**
     * Constructor
     */
    public Jump(boolean onlyForward) {
        this.onlyForward = onlyForward;
    }


    @Override
    public Set<Coordinates> getAllowedMoves(GameBoard gameBoard, Coordinates position) {

        var result = new HashSet<Coordinates>();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 1),
                Pair.of(-1, 1)));
        if (!onlyForward) {
            coordinates.add(Pair.of(-1, -1));
            coordinates.add(Pair.of(1, -1));
        }
        // blbost
        // neiplmelemntovane
        // treba rekurziu
        // todo implement
        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= GameBoard.SIZE / 2; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                var leftGoal = left + movement.getLeft();
                var rightGoal = right + movement.getRight();
                var goal = gameBoard.getColor(left, right);
                if (gameBoard.getColor(position).getOppositeColor().equals(goal)) {
                    if (gameBoard.getColor(leftGoal, rightGoal) == null) {
                        result.add(new Coordinates(left, right));
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }
}
