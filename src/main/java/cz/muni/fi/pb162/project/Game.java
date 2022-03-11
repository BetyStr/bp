package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Alzbeta Strompova
 */
public abstract class Game implements Prototype<Game>, Caretaker {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final Stack<Board.BoardState> savedBoardState = new Stack<>();

    private Board board = new Board();

    private Player playerOne;

    private Player playerTwo;
    private StateOfGame stateOfGame = StateOfGame.Playing;
    /**
     * Constructor who sets the first player according to the color of the players
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Game(Player playerOne, Player playerTwo) {
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

    public void setStateOfGame(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }


    public abstract void setInitialSet();

    public abstract void move(Coordinates oldPosition, Coordinates newPosition);

    private Coordinates getInputFromPlayer() {
        var position = SCANNER.next().trim();
        if (position.length() != 2) {
            throw new IllegalArgumentException("");
        }
        var letterNumber = position.charAt(0);
        // todo maybe exception
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return BoardNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    private Player getCurrentPlayer() {
        return playerOne.color().ordinal() == board.getRound() % 2 ? playerOne : playerTwo;
    }
    public void play() throws Exception {
        while (stateOfGame.equals(StateOfGame.Playing)) {
            var next = getCurrentPlayer();
            System.out.println(String.format("Next one is %s", next) + System.lineSeparator());
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            var piece = board.getPiece(fromPosition);

            if (piece == null) {
                throw new RuntimeException("on" + fromPosition + " is not any piece");
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
        if (savedBoardState.size() > 0) {
            board.restore(savedBoardState.pop());
        }
    }

    @Override
    public abstract Game clone();


}
