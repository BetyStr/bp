package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Knight implements AllowedMoves {

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();

        if (board.getColor(position.getLetterNumber() + 2, position.getNumber() + 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() + 1));
        }

        if (color.equals(board.getColor(position.getLetterNumber() + 2, position.getNumber() + 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() + 1));
        }


        if (board.getColor(position.getLetterNumber() + 2, position.getNumber() - 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() - 1));
        }

        if (color.equals(board.getColor(position.getLetterNumber() + 2, position.getNumber() - 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() - 1));
        }

        if (board.getColor(position.getLetterNumber() - 2, position.getNumber() + 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() + 1));
        }

        if (color.equals(board.getColor(position.getLetterNumber() - 2, position.getNumber() + 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() + 1));
        }
        if (board.getColor(position.getLetterNumber() - 2, position.getNumber() - 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() - 1));
        }

        if (color.equals(board.getColor(position.getLetterNumber() - 2, position.getNumber() - 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() - 1));
        }

        return result;
    }
}
