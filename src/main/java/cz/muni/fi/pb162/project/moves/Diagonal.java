package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Diagonal implements Move {

    private int step = Board.SIZE;

    /**
     * Constructor
     */
    public Diagonal() {
    }

    /**
     * Constructor
     * @param step max distance to move
     */
    public Diagonal(int step) {
        this.step = step;
    }

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {

        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();
        Color goal;
        for (int i = 1; i <= step ; i++) {

            goal = board.getColor(position.getLetterNumber() + i, position.getNumber() + i);
            if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.getLetterNumber() + i, position.getNumber() + i));
                break;
            }
            if (goal == null) {
                result.add(new Coordinates(position.getLetterNumber() + i, position.getNumber() + i));
            }
        }

        for (int i = 1; i <= step ; i++) {
            goal = board.getColor(position.getLetterNumber() - i, position.getNumber() + i);
            if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.getLetterNumber() - i, position.getNumber() + i));
                break;
            }
            if (goal == null) {
                result.add(new Coordinates(position.getLetterNumber() - i, position.getNumber() + i));
            }
        }

        for (int i = 1; i <= step ; i++) {
            goal = board.getColor(position.getLetterNumber() - i, position.getNumber() - i);
            if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.getLetterNumber() - i, position.getNumber() - i));
                break;
            }
            if (goal == null) {
                result.add(new Coordinates(position.getLetterNumber() - i, position.getNumber() - i));
            }
        }

        for (int i = 1; i <= step ; i++) {
            goal = board.getColor(position.getLetterNumber() + i, position.getNumber() - i);
            if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.getLetterNumber() + i, position.getNumber() - i));
                break;
            }
            if (goal == null) {
                result.add(new Coordinates(position.getLetterNumber() + i, position.getNumber() - i));
            }
        }

        return result;
    }
}
