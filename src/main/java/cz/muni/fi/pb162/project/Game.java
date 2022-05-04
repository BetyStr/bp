package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class representing the board game which has {@code Board.SIZE} x {@code Board.SIZE} squares.
 *
 * @author Alzbeta Strompova
 */
public abstract class Game implements Playable {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final Deque<Board> mementoHistory = new LinkedList<>();

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
    protected Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = new Board();
    }

    public Collection<Board> getMementoHistory() {
        return Collections.unmodifiableCollection(mementoHistory);
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
            hitSave();
            System.out.println(board);
        }
    }

    /**
     * Method that updates the status of game if necessary.
     */
    public abstract void updateStatus();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Patter Matching jdk17
        if (!(o instanceof Game game)) {
            return false;
        }
        return Objects.equals(playerOne, game.playerOne) && Objects.equals(playerTwo, game.playerTwo) &&
                stateOfGame == game.stateOfGame && Objects.equals(board, game.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOne, playerTwo, stateOfGame, board);
    }

    @Override
    public void hitSave() {
        mementoHistory.push(board.save());
    }

    @Override
    public void hitUndo() {
        if (!mementoHistory.isEmpty()) {
            board.restore(mementoHistory.pop());
        }
    }

}
