package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Game;
import java.util.Collection;
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
    public Set<Coordinate> getAllowedMoves(Game game, Coordinate position) {
        var board = game.getBoard();
        var result = new HashSet<Coordinate>();
        var color = board.getPiece(position).getColor().getOppositeColor();
        if (!(game instanceof Chess chess) || chess.isInDanger(position.letterNumber(), position.number(), color) ||
                pieceMoveAlready(chess.getMementoHistory(), position)) {
            return result;
        }
        bigCastling(position, board, result, color, chess);
        smallCastling(position, board, result, color, chess);
        return result;
    }

    private void bigCastling(Coordinate position, Board board, HashSet<Coordinate> result, Color color, Chess chess) {
        for (int i = 1; i < 4; i++) {
            if (!board.isEmpty(position.letterNumber() - i, position.number()) ||
                    chess.isInDanger(position.letterNumber() - i, position.number(), color)) {
                return;
            }
        }
        if (!pieceMoveAlready(chess.getMementoHistory(),
                new Coordinate(position.letterNumber() - 4, position.number()))) {
            result.add(new Coordinate(position.letterNumber() - 2, position.number()));

        }
    }

    private void smallCastling(Coordinate position, Board board, HashSet<Coordinate> result,
                               Color color, Chess chess) {
        for (int i = 1; i < 3; i++) {
            if (!board.isEmpty(position.letterNumber() + i, position.number()) ||
                    chess.isInDanger(position.letterNumber() + i, position.number(), color)) {
                return;
            }
        }
        if (!pieceMoveAlready(chess.getMementoHistory(),
                new Coordinate(position.letterNumber() + 3, position.number()))) {
            result.add(new Coordinate(position.letterNumber() + 2, position.number()));

        }
    }

    private boolean pieceMoveAlready(Collection<Board> memory, Coordinate position) {
        var piece = memory.iterator().next().getPiece(position);
        return !memory.stream().allMatch(board -> piece.equals(board.getPiece(position)));
    }
}
