package cz.muni.fi.pb162.project;

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
        super(playerOne, playerTwo, new Board());
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
        if (!piece.getTypeOfPiece().equals(PieceType.KING)) {
            return;
        }
        int diff = Math.abs(oldPosition.letterNumber() - newPosition.letterNumber());
        if (diff > 1) {
            putPieceOnBoard(oldPosition.letterNumber() + 1, oldPosition.number(),
                    getBoard().getPiece(oldPosition.letterNumber() + diff + 1, oldPosition.number()));
            putPieceOnBoard(oldPosition.letterNumber() + diff + 1, oldPosition.number(), null);
        }

    }

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        checkCastling(oldPosition, newPosition);
        putPieceOnBoard(newPosition.letterNumber(), newPosition.number(), piece);
        putPieceOnBoard(oldPosition.letterNumber(), oldPosition.number(), null);
        // promotion
        if ((newPosition.number() == 0 || newPosition.number() == 7)
                && piece.getTypeOfPiece().equals(PieceType.PAWN)) {
            piece.setTypeOfPiece(PieceType.QUEEN);
        }
    }

    @Override
    public void updateStatus() {
        var kings = getBoard()
                .getAllPiecesFromBoard()
                .stream()
                .filter(x -> x.getTypeOfPiece().equals(PieceType.KING))
                .toList();
        if (kings.size() < 2) {
            setStateOfGame(kings.get(0).getColor().equals(Color.WHITE)
                    ? StateOfGame.BLACK_PLAYER_WIN
                    : StateOfGame.WHITE_PLAYER_WIN);
        }
    }

}
