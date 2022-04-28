package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class representing board game which have {@code Board.SIZE} x {@code Board.SIZE} squares
 *
 * @author Alzbeta Strompova
 */
public abstract class Game implements Playable, Caretaker {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final Deque<Board> mementoHistory = new LinkedList<>();

    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private StateOfGame stateOfGame = StateOfGame.PLAYING;

    /**
     * Protected constructor because Builder.
     *
     * @param playerOne first of two players needed to play board game.
     * @param playerTwo second of two players needed to play board game.
     */
    protected Game(Player playerOne, Player playerTwo, Board board) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
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
     * Method that put piece on board at coordinates {@code letterNumber} and {@code number}.
     *
     * @param letterNumber first coordinate to put piece 0-7
     * @param number       second coordinate to put piece 0-7
     * @param piece        Piece which we want to put on board
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
        while (stateOfGame.equals(StateOfGame.PLAYING)) {
            System.out.println(board);
            var next = getCurrentPlayer();
            updateStatus();
            System.out.printf("Next one is %s%n", next);
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            board.setRound(board.getRound() + 1);
            move(fromPosition, toPosition);
            hitSave();
        }
        System.out.println(board);
    }

    /**
     * Method that control if it needs to be change status of game.
     */
    public abstract void updateStatus();

    /**
     * Method that return set of all possible moves than can do current player.
     *
     * @return set of all possible moves than can do current player.
     */
    public Set<Coordinates> allPossibleMovesByCurrentPlayer() {
        return board.getAllPiecesFromBoard()
                .stream()
                .filter(x -> x.getColor().equals(getCurrentPlayer().color()))
                .map(x -> x.getAllPossibleMoves(this))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
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
        return !Objects.equals(playerOne, game.playerOne) || !Objects.equals(playerTwo, game.playerTwo) ||
                stateOfGame != game.stateOfGame || !Objects.equals(board, game.board);
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
