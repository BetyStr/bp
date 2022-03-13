package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Coordinates;
import java.util.Collections;
import cz.muni.fi.pb162.project.Board;

import java.util.Set;


/**
 * todo castling, maybe use memento
 * @author Alzbeta Strompova
 */
public class Castling implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {

//        var whiteKing = gameBoard.getPiece(new Coordinates(4, 0));
//        var blackKing = gameBoard.getPiece(new Coordinates(4, 8));
//        var whiteRook = gameBoard.getPiece(new Coordinates(4, 0));
//        var blackRook = gameBoard.getPiece(new Coordinates(4, 8));
//
//        if (whiteKing != null && whiteKing.getColor().equals(Color.White)
//                && whiteKing.getType().equals(TypeOfPiece.King) && whiteKing.isFirstMove()) {
//            var whiteBigRook = gameBoard.getPiece(new Coordinates(0, 0));
//            var whiteSmallRook = gameBoard.getPiece(new Coordinates(7, 0));
//
//            if (whiteBigRook != null && whiteBigRook.getColor().equals(Color.White)
//                    && whiteBigRook.getType().equals(TypeOfPiece.Rook) && whiteBigRook.isFirstMove()) {
//                var canMove = true;
//                for (int i = 1; i < 3; i++) {
//                    var controlPosition = new Coordinates(i, 0);
//                    if (gameBoard.getPiece(controlPosition) != null
//                            || gameBoard.isInDanger(controlPosition, Color.Black)) {
//                        canMove = false;
//                        break;
//                    }
//                }
//                if (canMove) {
//                    //
//                }
//            }
//
//        }
        return Collections.emptySet();
    }
}
