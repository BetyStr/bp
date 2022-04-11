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

    /**
     * Private constructor because design pattern prototype
     *
     * @param target game to copy
     */
    private Chess(Game target) {
        super(target);
    }

    /**
     * Private constructor because design pattern builder
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     * @param board     is 2-dimensional array to represent board of pieces
     */
    private Chess(Player playerOne, Player playerTwo, Board board) {
        super(playerOne, playerTwo, board);
    }

    @Override
    public void setInitialSet() {
        putPieceOnBoard(4, 0, new Piece(Color.WHITE, TypeOfPiece.KING));
        putPieceOnBoard(3, 0, new Piece(Color.WHITE, TypeOfPiece.QUEEN));
        putPieceOnBoard(0, 0, new Piece(Color.WHITE, TypeOfPiece.ROOK));
        putPieceOnBoard(7, 0, new Piece(Color.WHITE, TypeOfPiece.ROOK));
        putPieceOnBoard(1, 0, new Piece(Color.WHITE, TypeOfPiece.KNIGHT));
        putPieceOnBoard(6, 0, new Piece(Color.WHITE, TypeOfPiece.KNIGHT));
        putPieceOnBoard(2, 0, new Piece(Color.WHITE, TypeOfPiece.BISHOP));
        putPieceOnBoard(5, 0, new Piece(Color.WHITE, TypeOfPiece.BISHOP));

        putPieceOnBoard(4, 7, new Piece(Color.BLACK, TypeOfPiece.KING));
        putPieceOnBoard(3, 7, new Piece(Color.BLACK, TypeOfPiece.QUEEN));
        putPieceOnBoard(0, 7, new Piece(Color.BLACK, TypeOfPiece.ROOK));
        putPieceOnBoard(7, 7, new Piece(Color.BLACK, TypeOfPiece.ROOK));
        putPieceOnBoard(1, 7, new Piece(Color.BLACK, TypeOfPiece.KNIGHT));
        putPieceOnBoard(6, 7, new Piece(Color.BLACK, TypeOfPiece.KNIGHT));
        putPieceOnBoard(2, 7, new Piece(Color.BLACK, TypeOfPiece.BISHOP));
        putPieceOnBoard(5, 7, new Piece(Color.BLACK, TypeOfPiece.BISHOP));

        for (int i = 0; i < Board.SIZE; i++) {
            putPieceOnBoard(i, 1, new Piece(Color.WHITE, TypeOfPiece.PAWN));
            putPieceOnBoard(i, 6, new Piece(Color.BLACK, TypeOfPiece.PAWN));
        }
    }

    private void checkCastling(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        if (!piece.getTypeOfPiece().equals(TypeOfPiece.KING)) {
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
                && piece.getTypeOfPiece().equals(TypeOfPiece.PAWN)) {
            piece.setTypeOfPiece(TypeOfPiece.QUEEN);
        }
    }

    @Override
    public void updateStatus() {
        if (allPossibleMovesByCurrentPlayer().isEmpty()) {
            setStateOfGame(StateOfGame.PAT);
        }
        var kings = getBoard()
                .getAllPiecesFromBoard()
                .stream()
                .filter(x -> x.getTypeOfPiece().equals(TypeOfPiece.KING))
                .toList();
        if (kings.size() < 2) {
            setStateOfGame(kings.get(0).getColor().equals(Color.WHITE)
                    ? StateOfGame.BLACK_PLAYER_WIN
                    : StateOfGame.WHITE_PLAYER_WIN);
        }
    }

    ///region Prototype
    @Override
    public Playable makeClone() {
        return new Chess(this);
    }
    ///endregion Prototype

}
