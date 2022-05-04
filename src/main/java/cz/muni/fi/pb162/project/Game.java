package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class representing the board game which has {@code Board.SIZE} x {@code Board.SIZE} squares.
 *
 * @author Alzbeta Strompova
 */
public class Game implements Playable {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private StateOfGame stateOfGame = StateOfGame.PLAYING;

    /**
     * Constructor.
     *
     * @param playerOne first of two players playing the board game.
     * @param playerTwo second of two players playing the board game.
     */
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getCurrentPlayer() {
        return playerOne.color().ordinal() == board.getRound() % 2 ? playerOne : playerTwo;
    }

    public StateOfGame getStateOfGame() {
        return stateOfGame;
    }

    public void setStateOfGame(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    /**
     * Method that puts the piece on the board at coordinates {@code letterNumber} and {@code number}.
     *
     * @param letterNumber first part of coordinate to put piece in range 0-7.
     * @param number       second part of coordinate to put piece in range 0-7.
     * @param piece        piece to put on the board.
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        board.putPieceOnBoard(letterNumber, number, piece);
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

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
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

    private Coordinates getInputFromPlayer() {
        var position = SCANNER.next().trim();
        var letterNumber = position.charAt(0);
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return BoardNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    @Override
    public void play() {
        System.out.println(board);
        while (stateOfGame.equals(StateOfGame.PLAYING)) {
            var next = getCurrentPlayer();
            updateStatus();
            System.out.printf("Next one is %s%n", next);
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            board.setRound(board.getRound() + 1);
            move(fromPosition, toPosition);
            System.out.println(board);
        }
    }

    /**
     * Method that control if it needs to be change status of game.
     */
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
