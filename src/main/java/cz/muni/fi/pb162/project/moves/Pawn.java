package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Pawn implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();
        if (color.equals(Color.White)) {
            result.add(new Coordinates(position.letterNumber(), position.number() + 1));
            if (position.number() == 1) {
                result.add(new Coordinates(position.letterNumber(), 3));
            }
            if (!board.isEmpty(position.letterNumber() + 1, position.number() + 1)
                    && board.getColor(position.letterNumber() + 1,
                    position.number() + 1).equals(Color.Black)) {
                result.add(new Coordinates(position.letterNumber() + 1, position.number() + 1));
            }
            if (!board.isEmpty(position.letterNumber() - 1, position.number() + 1)
                    && board.getColor(position.letterNumber() - 1,
                    position.number() + 1).equals(Color.Black)) {
                result.add(new Coordinates(position.letterNumber() - 1, position.number() + 1));
            }
        }
        extracted(board, position, result, color);
        // todo en passaint..memento
        return result;
    }

    private void extracted(Board board, Coordinates position, HashSet<Coordinates> result, Color color) {
        if (color.equals(Color.Black)) {
            result.add(new Coordinates(position.letterNumber(), position.number() - 1));
            if (position.number() == 6) {
                result.add(new Coordinates(position.letterNumber(), 4));
            }
            if (!board.isEmpty(position.letterNumber() + 1, position.number() - 1)
                    && board.getColor(position.letterNumber() + 1,
                    position.number() - 1).equals(Color.White)) {
                result.add(new Coordinates(position.letterNumber() + 1, position.number() - 1));
            }
            if (!board.isEmpty(position.letterNumber() - 1, position.number() - 1)
                    && board.getColor(position.letterNumber() - 1,
                    position.number() - 1).equals(Color.White)) {
                result.add(new Coordinates(position.letterNumber() - 1, position.number() - 1));
            }
        }
    }
}
