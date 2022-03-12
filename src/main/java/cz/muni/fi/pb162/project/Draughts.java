package cz.muni.fi.pb162.project;

/**
 * @author Alzbeta Strompova
 */
public class Draughts extends Game {

    public Draughts(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    protected Draughts(Game target) {
        super(target);
    }

    @Override
    public void setInitialSet() {
        setBoard(new Board());
        for (int i = 0; i < Board.SIZE; i += 2) {
            getBoard().putPieceOnBoard(0, i, new Piece(Color.WHITE, TypeOfPiece.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(2, i, new Piece(Color.WHITE, TypeOfPiece.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(6, i, new Piece(Color.BLACK, TypeOfPiece.DRAUGHTS_MAN));
        }
        for (int i = 1; i < Board.SIZE; i += 2) {
            getBoard().putPieceOnBoard(1, i, new Piece(Color.WHITE, TypeOfPiece.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(5, i, new Piece(Color.BLACK, TypeOfPiece.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(7, i, new Piece(Color.BLACK, TypeOfPiece.DRAUGHTS_MAN));
        }
    }

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {

    }

    @Override
    public Game clone() {
        return new Draughts(this);
    }

}
