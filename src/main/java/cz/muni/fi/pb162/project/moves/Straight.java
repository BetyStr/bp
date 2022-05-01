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
public class Straight implements Move {

    private int step = Board.SIZE;

    /**
     * Constructor
     * step -> size of game board
     */
    public Straight() {
    }

    /**
     * Constructor
     *
     * @param step max distance to move
     */
    public Straight(int step) {
        this.step = step;
    }

    @Override
    public Set<Coordinate> getAllowedMoves(Game game, Coordinate position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinate>();
        var color = board.getPiece(position).getColor();

        var coordinates = new HashSet<>(Arrays.asList(
                Pair.of(0, 1),
                Pair.of(0, -1),
                Pair.of(1, 0),
                Pair.of(-1, 0)));

        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= step; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                var coordinate = new Coordinate(left, right);
                if (!Board.inRange(coordinate)) {
                    break;
                }
                if (board.getColor(left, right) == null) {
                    result.add(coordinate);
                } else {
                    if (color.getOppositeColor().equals(board.getColor(left, right))) {
                        result.add(coordinate);
                    }
                    break;
                }
            }
        }
        return result;
    }

}
