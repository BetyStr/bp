package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Pawn implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();
        if (color.equals(Color.WHITE)) {
            movesByWhite(board, position, result);
        }
        if (color.equals(Color.BLACK)) {
            movesByBlack(board, position, result);
        }
        return result;
    }

    private void movesByWhite(Board board, Coordinates position, HashSet<Coordinates> result) {
        result.add(new Coordinates(position.letterNumber(), position.number() + 1));
        if (position.number() == 1) {
            result.add(new Coordinates(position.letterNumber(), 3));
        }
        if (!board.isEmpty(position.letterNumber() + 1, position.number() + 1)
                && board.getColor(position.letterNumber() + 1,
                position.number() + 1).equals(Color.BLACK)) {
            result.add(new Coordinates(position.letterNumber() + 1, position.number() + 1));
        }
        if (!board.isEmpty(position.letterNumber() - 1, position.number() + 1)
                && board.getColor(position.letterNumber() - 1,
                position.number() + 1).equals(Color.BLACK)) {
            result.add(new Coordinates(position.letterNumber() - 1, position.number() + 1));
        }
    }

    private void movesByBlack(Board board, Coordinates position, HashSet<Coordinates> result) {
        result.add(new Coordinates(position.letterNumber(), position.number() - 1));
        if (position.number() == 6) {
            result.add(new Coordinates(position.letterNumber(), 4));
        }
        if (!board.isEmpty(position.letterNumber() + 1, position.number() - 1)
                && board.getColor(position.letterNumber() + 1,
                position.number() - 1).equals(Color.WHITE)) {
            result.add(new Coordinates(position.letterNumber() + 1, position.number() - 1));
        }
        if (!board.isEmpty(position.letterNumber() - 1, position.number() - 1)
                && board.getColor(position.letterNumber() - 1,
                position.number() - 1).equals(Color.WHITE)) {
            result.add(new Coordinates(position.letterNumber() - 1, position.number() - 1));
        }
    }
}

