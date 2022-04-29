package cz.muni.fi.pb162.project;

import java.util.Arrays;

/**
 * Class for representing simplification of board game Draughts.
 * Subclass of abstract class {@code Game}.
 *
 * @author Alzbeta Strompova
 */
public class Draughts extends Game {

    /**
     * Constructor who takes two players with opposite color.
     *
     * @param playerOne first of two players needed to play draughts.
     * @param playerTwo second of two players needed to play draughts.
     */
    public Draughts(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }


    @Override
    public void setInitialSet() {
        for (int i = 0; i < Board.SIZE; i += 2) {
            getBoard().putPieceOnBoard(i, 0, new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(i, 2, new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(i, 6, new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        }
        for (int i = 1; i < Board.SIZE; i += 2) {
            getBoard().putPieceOnBoard(i, 1, new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(i, 5, new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(i, 7, new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        }
    }

    @Override
    public void move(Coordinate oldPosition, Coordinate newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        putPieceOnBoard(newPosition.letterNumber(), newPosition.number(), piece);
        putPieceOnBoard(oldPosition.letterNumber(), oldPosition.number(), null);
        if (Math.abs(newPosition.number() - oldPosition.number()) > 1) {
            var x = oldPosition.letterNumber() + (newPosition.letterNumber() - oldPosition.letterNumber()) / 2;
            var y = oldPosition.number() + (newPosition.number() - oldPosition.number()) / 2;
            putPieceOnBoard(x, y, null);
        }
    }

    @Override
    public void updateStatus() {
        if (checkWinner(Color.WHITE)) {
            setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        }
        if (checkWinner(Color.BLACK)) {
            setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        }
    }

    private boolean checkWinner(Color color) {
        return Arrays.stream(getBoard()
                        .getAllPiecesFromBoard())
                .filter(x -> x.getColor().equals(color.getOppositeColor()))
                .toList()
                .isEmpty();

    }

}
