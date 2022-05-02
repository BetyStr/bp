package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Game;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Part of Strategy design pattern.
 *
 * @author Alzbeta Strompova
 */
public class Jump implements Move {

    private boolean onlyForward = false;

    /**
     * Constructor
     * onlyForward -> false
     */
    public Jump() {
    }

    /**
     * Constructor
     *
     * @param onlyForward boolean that decide that piece can only move forward
     */
    public Jump(boolean onlyForward) {
        this.onlyForward = onlyForward;
    }


    @Override
    public Set<Coordinate> getAllowedMoves(Game game, Coordinate position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinate>();
        var color = board.getColor(position.letterNumber(), position.number());
        var coordinates = Move.getDiagonalShift(onlyForward, color);

        for (Pair<Integer, Integer> movement : coordinates) {
            var leftToJump = position.letterNumber() + movement.getLeft();
            var rightToJump = position.number() + movement.getRight();
            var leftGoal = leftToJump + movement.getLeft();
            var rightGoal = rightToJump + movement.getRight();
            if (color.getOppositeColor().equals(board.getColor(leftToJump, rightToJump))
                    && board.getPiece(leftGoal, rightGoal) == null
                    && Board.inRange(new Coordinate(leftGoal, rightGoal))) {
                result.add(new Coordinate(leftGoal, rightGoal));
            }
        }
        return result;
    }
}
