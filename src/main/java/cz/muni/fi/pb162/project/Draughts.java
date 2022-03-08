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
        board = new Piece[SIZE][SIZE];
        for (int i = 0; i < SIZE; i += 2) {
            putPieceOnBoard(0, i, new Piece(Color.White, TypeOfPiece.DraughtsKing));
            putPieceOnBoard(2, i, new Piece(Color.White, TypeOfPiece.DraughtsKing));
            putPieceOnBoard(6, i, new Piece(Color.Black, TypeOfPiece.DraughtsKing));
        }
        for (int i = 1; i < SIZE; i += 2) {
            putPieceOnBoard(1, i, new Piece(Color.White, TypeOfPiece.DraughtsKing));
            putPieceOnBoard(5, i, new Piece(Color.Black, TypeOfPiece.DraughtsKing));
            putPieceOnBoard(7, i, new Piece(Color.Black, TypeOfPiece.DraughtsKing));
        }
    }

    @Override
    public String move(Coordinates oldPosition, Coordinates newPosition) {
        // todo
        return null;
    }

    @Override
    public Game clone() {
        return new Draughts(this);
    }

}
