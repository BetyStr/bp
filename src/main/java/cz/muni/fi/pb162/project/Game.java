package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;
import java.util.Scanner;

/**
 * Class representing board game which have {@code Board.SIZE} x {@code Board.SIZE} squares.
 *
 * @author Alzbeta Strompova
 */
public abstract class Game implements Playable {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private StateOfGame stateOfGame = StateOfGame.PLAYING;

    /**
     * Constructor.
     *
     * @param playerOne first of two players needed to play board game.
     * @param playerTwo second of two players needed to play board game.
     */
    protected Game(Player playerOne, Player playerTwo) {
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
     * Method that put piece on board at coordinates {@code letterNumber} and {@code number}.
     *
     * @param letterNumber first coordinate to put piece 0-7
     * @param number       second coordinate to put piece 0-7
     * @param piece        Piece which we want to put on board
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        board.putPieceOnBoard(letterNumber, number, piece);
    }

    private Coordinate getInputFromPlayer() {
        var position = SCANNER.next().trim();
        var letterNumber = position.charAt(0);
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return BoardNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    @Override
    public void play() {
        while (stateOfGame.equals(StateOfGame.PLAYING)) {
            var next = getCurrentPlayer();
            updateStatus();
            System.out.printf("Next one is %s%n", next);
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            board.setRound(board.getRound() + 1);
            move(fromPosition, toPosition);
        }
        System.out.println(board);
    }

    /**
     * Method that control if it needs to be change status of game.
     */
    public abstract void updateStatus();

}
