package cz.muni.fi.pb162.project;

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
        super(playerOne, playerTwo, new Board());
    }

    @Override
    public void setInitialSet() {
        var factory = new DraughtsPieceFactory();
        for (int i = 0; i < Board.SIZE; i += 2) {
            getBoard().putPieceOnBoard(i, 0, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE));
            getBoard().putPieceOnBoard(i, 2, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE));
            getBoard().putPieceOnBoard(i, 6, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK));
        }
        for (int i = 1; i < Board.SIZE; i += 2) {
            getBoard().putPieceOnBoard(i, 1, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE));
            getBoard().putPieceOnBoard(i, 5, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK));
            getBoard().putPieceOnBoard(i, 7, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK));
        }
    }

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
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
        if (getBoard().getAllPiecesFromBoard()
                .stream()
                .filter(x -> x.getColor().equals(getCurrentPlayer().color()))
                .toList()
                .isEmpty()) {
            setStateOfGame(getCurrentPlayer().color().equals(Color.WHITE)
                    ? StateOfGame.BLACK_PLAYER_WIN
                    : StateOfGame.WHITE_PLAYER_WIN);
        }
    }

}
