package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class representing board game which have {@code Board.SIZE} x {@code Board.SIZE} squares.
 *
 * @author Alzbeta Strompova
 */
public abstract class Game implements Playable {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Deque<Board> mementoHistory = new LinkedList<>();
    private Board board;
    private Player playerOne;
    private Player playerTwo;
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

    /**
     * Protected constructor because Prototype.
     */
    protected Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            stateOfGame = target.stateOfGame;
            board = target.board;
            mementoHistory = target.mementoHistory;
        }
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

    private Coordinate getInputFromPlayer() {
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
     * Method that control if it needs to be change status of game.
     */
    public abstract void updateStatus();

    /**
     * Method that return ordered set of all possible moves than can do current player.
     *
     * @return ordered set of all possible moves than can do current player.
     */
    public Set<Coordinate> allPossibleMovesByCurrentPlayer() {
        var inverseComparator = new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return -1 * o1.compareTo(o2);
            }
        };
        var result = new TreeSet<>(inverseComparator);
        result.addAll(Arrays.stream(board.getAllPiecesFromBoard())
                .filter(x -> x.getColor().equals(getCurrentPlayer().color()))
                .map(x -> x.getAllPossibleMoves(this))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet()));
        return result;
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
