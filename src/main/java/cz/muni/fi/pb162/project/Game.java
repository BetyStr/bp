package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.InvalidFormatOfInputException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;
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
 * Class representing the board game which has {@code Board.SIZE} x {@code Board.SIZE} squares.
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
     * @param playerOne first of two players playing the board game.
     * @param playerTwo second of two players playing the board game.
     */
    protected Game(Player playerOne, Player playerTwo) {
        this(playerOne, playerTwo, new Board());
    }

    /**
     * Constructor of design pattern Builder.
     *
     * @param playerOne first of two players playing board game.
     * @param playerTwo second of two players playing board game.
     * @param board     playing board of the game.
     */
    protected Game(Player playerOne, Player playerTwo, Board board) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
    }

    /**
     * Protected constructor of design pattern Prototype.
     *
     * @param target is the game to copy.
     */
    protected Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            stateOfGame = target.stateOfGame;
            board = target.board.makeClone();
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
        if (position.length() != 2) {
            throw new InvalidFormatOfInputException("Format of input must by [a-h][1-8].");
        }
        var letterNumber = position.charAt(0);
        int number;
        try {
            number = Integer.parseInt(String.valueOf(position.charAt(1)));
        } catch (NumberFormatException ex) {
            throw new InvalidFormatOfInputException("Format of input must by [a-h][1-8].", ex);
        }
        return BoardNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    @Override
    public void play() throws EmptySquareException, NotAllowedMoveException {
        System.out.println(board);
        while (stateOfGame.equals(StateOfGame.PLAYING)) {
            var next = getCurrentPlayer();
            updateStatus();
            System.out.printf("Next one is %s%n", next);
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            var piece = board.getPiece(fromPosition);
            if (piece == null) {
                throw new EmptySquareException("On" + fromPosition + " is not any piece");
            }
            if (!piece.getAllPossibleMoves(this).contains(toPosition)) {
                throw new NotAllowedMoveException(piece + "can move to " + toPosition);
            }
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

    /**
     * Method that returns the ordered set of all possible moves available for current player.
     *
     * @return ordered set of all possible moves available for current player.
     */
    public Set<Coordinates> allPossibleMovesByCurrentPlayer() {
        var inverseComparator = new Comparator<Coordinates>() {
            @Override
            public int compare(Coordinates o1, Coordinates o2) {
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
