package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


/**
 * Part of Strategy design pattern.
 * Castling is specific move for Chess.
 *
 * @author Alzbeta Strompova
 */
public class Castling implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinates>();

        var color = board.getPiece(position).getColor().getOppositeColor();

        if (!(game instanceof Chess chess) || chess.isInDanger(position.letterNumber(), position.number(), color) ||
                pieceMoveAlready(chess.getMementoHistory(), position)) {
            return result;
        }
        bigCastling(position, board, result, color, chess);
        smallCastling(position, board, result, color, chess);
        return result;
    }

    private void bigCastling(Coordinates position, Board board, HashSet<Coordinates> result, Color color, Chess chess) {
        for (int i = 1; i < 4; i++) {
            if (!board.isEmpty(position.letterNumber() - i, position.number()) ||
                    chess.isInDanger(position.letterNumber() - i, position.number(), color)) {
                return;
            }
        }
        if (!pieceMoveAlready(chess.getMementoHistory(),
                new Coordinates(position.letterNumber() - 4, position.number()))) {
            result.add(new Coordinates(position.letterNumber() - 2, position.number()));

        }
    }

    private void smallCastling(Coordinates position, Board board, HashSet<Coordinates> result,
                               Color color, Chess chess) {
        for (int i = 1; i < 3; i++) {
            if (!board.isEmpty(position.letterNumber() + i, position.number()) ||
                    chess.isInDanger(position.letterNumber() + i, position.number(), color)) {
                return;
            }
        }
        if (!pieceMoveAlready(chess.getMementoHistory(),
                new Coordinates(position.letterNumber() + 3, position.number()))) {
            result.add(new Coordinates(position.letterNumber() + 2, position.number()));

        }
    }

    private boolean pieceMoveAlready(Deque<Board> memory, Coordinates position) {
        var piece = memory.peek() != null ? memory.peek().getPiece(position) : null;
        return !memory.stream().allMatch(board -> board.getPiece(position).equals(piece));
    }
}
