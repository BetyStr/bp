package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.InvalidFormatOfInputException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Abstract class representing Game on board
 * which have {@code Board.SIZE} x {@code Board.SIZE} squares
 *
 * @author Alzbeta Strompova
 */
public abstract class Game implements Prototype<Game>, Caretaker {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final Deque<Board> savedBoardState = new LinkedList<>();

    private Board board = new Board();
    private Player playerOne;
    private Player playerTwo;
    private StateOfGame stateOfGame = StateOfGame.PLAYING;

    /**
     * Constructor
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    protected Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    /**
     * Constructor because Prototype
     */
    protected Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            stateOfGame = target.stateOfGame;
            board = target.board;
        }
    }

    protected Board getBoard() {
        return board;
    }

    protected void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @param letterNumber first coordinate to put piece 0-7
     * @param number       second coordinate to put piece 0-7
     * @param piece        Piece which we want to put on board
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        board.putPieceOnBoard(letterNumber, number, piece);
    }

    public void setStateOfGame(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    private Player getCurrentPlayer() {
        return playerOne.color().ordinal() == board.getRound() % 2 ? playerOne : playerTwo;
    }

    public abstract void setInitialSet();

    public abstract void move(Coordinates oldPosition, Coordinates newPosition);

    public void play() {
        while (stateOfGame.equals(StateOfGame.PLAYING)) {
            var next = getCurrentPlayer();
            //todo check if exist move
            System.out.printf("Next one is %s%n", next);
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            var piece = board.getPiece(fromPosition);
            if (piece == null) {
                throw new EmptySquareException("On" + fromPosition + " is not any piece");
            }
            if (!piece.getAllPossibleMoves(board).contains(toPosition)) {
                throw new NotAllowedMoveException(piece + "can move to " + toPosition);
            }
            board.setRound(board.getRound() + 1);
            move(fromPosition, toPosition);
            System.out.println(board);
            hitSave();
        }
    }

    private Coordinates getInputFromPlayer() {
        var position = SCANNER.next().trim();
        if (position.length() != 2) {
            throw new InvalidFormatOfInputException("Format of input must by [a-h][1-8]");
        }
        var letterNumber = position.charAt(0);
        var number = 0;
        try {
            number = Integer.parseInt(String.valueOf(position.charAt(1)));
        } catch (NumberFormatException ex) {
            throw new InvalidFormatOfInputException("Format of input must by [a-h][1-8]");
        }
        return BoardNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Patter Matching jdk17
        if (!(o instanceof Game game)) {
            return false;
        }
        return !Objects.equals(playerOne, game.playerOne) ||
                !Objects.equals(playerTwo, game.playerTwo) ||
                stateOfGame != game.stateOfGame ||
                board.equals(game.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOne, playerTwo, stateOfGame, board);
    }

    @Override
    public void hitSave() {
        savedBoardState.push(board.save());
    }

    @Override
    public void hitUndo() {
        if (!savedBoardState.isEmpty()) {
            board.restore(savedBoardState.pop());
        }
    }

}
