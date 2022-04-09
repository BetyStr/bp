package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.InvalidFormatOfInputException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;
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
 * Abstract Factory is a creational design pattern that lets you produce
 * families of related objects without specifying their concrete classes. <p>
 * Abstract class representing board game which have {@code Board.SIZE} x {@code Board.SIZE} squares
 *
 * @author Alzbeta Strompova
 */
public abstract class Game implements Playable {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final Deque<Board> mementoHistory = new LinkedList<>();

    private Board board;
    private Player playerOne;
    private Player playerTwo;
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

    /**
     * Protected constructor because Prototype.
     */
    protected Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            stateOfGame = target.stateOfGame;
            board = target.board;
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

    protected Player getCurrentPlayer() {
        return playerOne.color().ordinal() == board.getRound() % 2 ? playerOne : playerTwo;
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
    public void play() throws EmptySquareException, NotAllowedMoveException {
        while (stateOfGame.equals(StateOfGame.PLAYING)) {
            System.out.println(board);
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
                stateOfGame != game.stateOfGame || board.equals(game.board);
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
