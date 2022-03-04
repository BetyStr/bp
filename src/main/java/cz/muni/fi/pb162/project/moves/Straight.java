package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Straight implements AllowedMoves {

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

        for (int i = 1; i <= step ; i++) {
            if (color.getOppositeColor().equals(board.getColor(position.getLetterNumber() + i, position.getNumber()))) {
                result.add(new Coordinates(position.getLetterNumber() + i, position.getNumber()));
                break;
            }
            if (board.getColor(position.getLetterNumber() + i, position.getNumber()).getOppositeColor() == null) {
                result.add(new Coordinates(position.getLetterNumber() + i, position.getNumber()));
            }
        }

        for (int i = 1; i <= step ; i++) {
            if (color.getOppositeColor().equals(board.getColor(position.getLetterNumber() - i, position.getNumber()))) {
                result.add(new Coordinates(position.getLetterNumber() - i, position.getNumber()));
                break;
            }
            if (board.getColor(position.getLetterNumber() - i, position.getNumber()).getOppositeColor() == null) {
                result.add(new Coordinates(position.getLetterNumber() - i, position.getNumber()));
            }
        }

        for (int i = 1; i <= step ; i++) {
            if (color.getOppositeColor().equals(board.getColor(position.getLetterNumber(), position.getNumber() + i))) {
                result.add(new Coordinates(position.getLetterNumber(), position.getNumber() + i));
                break;
            }
            if (board.getColor(position.getLetterNumber(), position.getNumber() + i).getOppositeColor() == null) {
                result.add(new Coordinates(position.getLetterNumber(), position.getNumber() + i));
            }
        }

        for (int i = 1; i <= step ; i++) {
            if (color.getOppositeColor().equals(board.getColor(position.getLetterNumber(), position.getNumber() - i))) {
                result.add(new Coordinates(position.getLetterNumber(), position.getNumber() - i));
                break;
            }
            if (board.getColor(position.getLetterNumber(), position.getNumber() - i).getOppositeColor() == null) {
                result.add(new Coordinates(position.getLetterNumber(), position.getNumber() - i));
            }
        }

        return result;
    }

}
