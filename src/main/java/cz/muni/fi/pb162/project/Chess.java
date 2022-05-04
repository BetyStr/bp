package cz.muni.fi.pb162.project;

import java.util.Arrays;

/**
 * Class for representing simplification of board game Chess.
 * Subclass of abstract class {@code Game}.
 *
 * @author Alzbeta Strompova
 */
public class Chess extends Game {

    /**
     * Constructor who takes two players with opposite color.
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public void setInitialSet() {
        putPieceOnBoard(4, 0, new Piece(Color.WHITE, PieceType.KING));
        putPieceOnBoard(3, 0, new Piece(Color.WHITE, PieceType.QUEEN));
        putPieceOnBoard(0, 0, new Piece(Color.WHITE, PieceType.ROOK));
        putPieceOnBoard(7, 0, new Piece(Color.WHITE, PieceType.ROOK));
        putPieceOnBoard(1, 0, new Piece(Color.WHITE, PieceType.KNIGHT));
        putPieceOnBoard(6, 0, new Piece(Color.WHITE, PieceType.KNIGHT));
        putPieceOnBoard(2, 0, new Piece(Color.WHITE, PieceType.BISHOP));
        putPieceOnBoard(5, 0, new Piece(Color.WHITE, PieceType.BISHOP));

        putPieceOnBoard(4, 7, new Piece(Color.BLACK, PieceType.KING));
        putPieceOnBoard(3, 7, new Piece(Color.BLACK, PieceType.QUEEN));
        putPieceOnBoard(0, 7, new Piece(Color.BLACK, PieceType.ROOK));
        putPieceOnBoard(7, 7, new Piece(Color.BLACK, PieceType.ROOK));
        putPieceOnBoard(1, 7, new Piece(Color.BLACK, PieceType.KNIGHT));
        putPieceOnBoard(6, 7, new Piece(Color.BLACK, PieceType.KNIGHT));
        putPieceOnBoard(2, 7, new Piece(Color.BLACK, PieceType.BISHOP));
        putPieceOnBoard(5, 7, new Piece(Color.BLACK, PieceType.BISHOP));

        for (int i = 0; i < Board.SIZE; i++) {
            putPieceOnBoard(i, 1, new Piece(Color.WHITE, PieceType.PAWN));
            putPieceOnBoard(i, 6, new Piece(Color.BLACK, PieceType.PAWN));
        }
    }

    private void checkCastling(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        if (!piece.getPieceType().equals(PieceType.KING)) {
            return;
        }
        if (Math.abs(oldPosition.letterNumber() - newPosition.letterNumber()) > 1) {
            if (newPosition.letterNumber() == 2) {
                move(new Coordinates(0, oldPosition.number()),
                        new Coordinates(3, oldPosition.number()));
            } else {
                move(new Coordinates(7, oldPosition.number()),
                        new Coordinates(5, oldPosition.number()));
            }
        }
    }

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        checkCastling(oldPosition, newPosition);
        putPieceOnBoard(newPosition.letterNumber(), newPosition.number(), piece);
        putPieceOnBoard(oldPosition.letterNumber(), oldPosition.number(), null);
        //promotion
        if ((newPosition.number() == 0 || newPosition.number() == 7)
                && piece.getPieceType().equals(PieceType.PAWN)) {
            piece.setPieceType(PieceType.QUEEN);
            putPieceOnBoard(newPosition.letterNumber(), newPosition.number(),
                    new Piece(piece.getColor(), PieceType.QUEEN));
        }
    }

    @Override
    public void updateStatus() {
        var kings = Arrays.stream(getBoard()
                        .getAllPiecesFromBoard())
                .filter(x -> x.getPieceType().equals(PieceType.KING))
                .toList();
        if (kings.size() < 2) {
            setStateOfGame(kings.get(0).getColor().equals(Color.WHITE)
                    ? StateOfGame.WHITE_PLAYER_WIN
                    : StateOfGame.BLACK_PLAYER_WIN);
        }
    }

}
