package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Game;

import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Castling implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        // todo castling

//        var whiteKing = (ChessPiece) game.getPiece(new Coordinates(4, 0));
//        var blackKing = (ChessPiece) game.getPiece(new Coordinates(4, 8));
//        var whiteRook = (ChessPiece) game.getPiece(new Coordinates(4, 0));
//        var blackRook = (ChessPiece) game.getPiece(new Coordinates(4, 8));
//
//        if (whiteKing != null && whiteKing.getColor().equals(Color.White)
//                && whiteKing.getType().equals(TypeOfChessPieces.King) && whiteKing.fistMove) {
//            var whiteBigRook = (ChessPiece) game.getPiece(new Coordinates(0, 0));
//            var whiteSmallRook = (ChessPiece) game.getPiece(new Coordinates(7, 0));
//
//            if (whiteBigRook != null && whiteBigRook.getColor().equals(Color.White)
//                    && whiteBigRook.getType().equals(TypeOfChessPieces.Rook) && whiteBigRook.fistMove
//                    && game.getPiece(new Coordinates(1, 0)) == null
//                    && game.getPiece(new Coordinates(2, 0)) == null
//                    && game.getPiece(new Coordinates(3, 0)) == null) {
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        var xx = Arrays.stream(game.board).map(Arrays::stream)
//                                .map(x -> x.filter(Objects::nonNull))
//                                .map(y -> y.filter(x -> x.getColor().equals(Color.Black)))
//                                .map(y -> y.map(x -> (x.getAllPossibleMoves(game))))
//                                .iterator()
//                                .next()
//                                .iterator()
//                                .next();
//                    }
//                }
//                }
//
//
//            if (whiteSmallRook != null && whiteSmallRook.getColor().equals(Color.White)
//                    && whiteSmallRook.getType().equals(TypeOfChessPieces.Rook) && whiteSmallRook.fistMove
//                    && game.getPiece(new Coordinates(5, 0)) == null
//                    && game.getPiece(new Coordinates(6, 0)) == null) {
//
//            }
//        }
        return null;
    }
}
