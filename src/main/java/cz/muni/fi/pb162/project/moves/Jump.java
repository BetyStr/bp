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
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinates>();

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 1),
                Pair.of(-1, 1)));
        if (!onlyForward) {
            coordinates.add(Pair.of(-1, -1));
            coordinates.add(Pair.of(1, -1));
        }

        for (Pair<Integer, Integer> movement : coordinates) {
            var leftToJump = position.letterNumber() + movement.getLeft();
            var rightToJump = position.number() + movement.getRight();
            var leftGoal = leftToJump + movement.getLeft();
            var rightGoal = rightToJump + movement.getRight();
            if (board.getColor(position).getOppositeColor().equals(board.getColor(leftToJump, rightToJump))
                    && board.getColor(leftGoal, rightGoal) == null) {
                result.add(new Coordinates(leftToJump, rightToJump));
            }
        }
        return result;
    }
}
