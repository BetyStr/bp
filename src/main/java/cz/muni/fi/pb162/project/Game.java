package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Alzbeta Strompova
 * todo read and write
 */
public abstract class Game implements Prototype<Game>, Originator<Game.GameState> {

    public final static int SIZE = 8;
    public final static char SPACE = '\u2003';
    private final static char SEPARATOR = '|';
    private final static Scanner scanner = new Scanner(System.in);

    protected Piece[][] board;

    private History history;
    private Player playerOne;
    private Player playerTwo;
    private Player next;
    private StateOfGame stateOfGame = StateOfGame.Playing;
    private int round;


    /**
     * Constructor who sets the first player according to the color of the players
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        history = new History(this);
        next = playerOne.color().equals(Color.White) ? playerOne : playerTwo;
    }

    /**
     * Constructor because Prototype
     */
    protected Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            next = target.next;
            board = target.board;
            round = target.round;
            history = target.history;
            stateOfGame = target.stateOfGame;
        }
    }

    public StateOfGame getStateOfGame() {
        return stateOfGame;
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

    public Player getNext() {
        return next;
    }

    private void nextTurn() {
        next = next.equals(playerOne) ? playerTwo : playerOne;
    }

    public Color getColor(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)) {
            return null;
        }
        return board[letterNumber][number].getColor();
    }

    public Color getColor(Coordinates position) {
        return getColor(position.letterNumber(), position.number());
    }

    public Piece getPiece(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)) {
            return null;
        }
        return board[letterNumber][number];
    }

    public Piece getPiece(Coordinates position) {
        return getPiece(position.letterNumber(), position.number());
    }

    /**
     * @param letterNumber first coordinate to put piece 0-7
     * @param number       second coordinate to put piece 0-7
     * @param piece        ChessPiece which we want to put on board
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        assert inRange(letterNumber, number);
        board[letterNumber][number] = piece;
    }

    public abstract void setInitialSet();

    public Coordinates findCoordinatesOfPieceById(long id) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != null && board[i][j].getId() == id) {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
    }

    private boolean inRange(int x, int y) {
        return x < SIZE && y < SIZE && x >= 0 && y >= 0;
    }

    public boolean inRange(Coordinates coordinates) {
        return inRange(coordinates.letterNumber(), coordinates.number());
    }

    public boolean isEmpty(Coordinates position) {
        return isEmpty(position.letterNumber(), position.number());
    }

    public boolean isEmpty(int x, int y) {
        return !inRange(x, y) || board[x][y] == null;
    }

    public abstract String move(Coordinates oldPosition, Coordinates newPosition);

    private Coordinates getInputFromPlayer() {
        var position = scanner.next().trim();
        if (position.length() != 2) {
            throw new IllegalArgumentException("");
        }
        var letterNumber = position.charAt(0);
        // todo maybe exception
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return ChessNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    public void play() throws Exception {
        while (stateOfGame.equals(StateOfGame.Playing)) {
            System.out.println(String.format("Next one is %s", getNext()) + System.lineSeparator());
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            var piece = getPiece(fromPosition);

            if (piece == null) {
                throw new RuntimeException("on" + fromPosition + " is not any piece");
            }
            if (!piece.getAllPossibleMoves(this).contains(toPosition)) {
                throw new NotAllowedMoveException(piece + "can move to " + toPosition);
            }
            round += 1;
            System.out.println(move(fromPosition, toPosition));
            nextTurn();
            System.out.println(printBoardToConsole());
            history.hitSave();
        }
    }

    public String printBoardToConsole() {
        var result = new StringBuilder();
        result.append(" ").append(SPACE);
        // numbers
        for (int i = 0; i < SIZE; i++) {
            result.append(SPACE).append(SPACE).append(i + 1).append(SPACE);
        }
        result.append(System.lineSeparator());

        for (int i = 0; i < SIZE; i++) {
            // board
            result.append("   ").append("-".repeat(47));
            result.append(System.lineSeparator());
            // letters
            result.append((char) (65 + i));
            // pieces
            for (int j = 0; j < SIZE; j++) {
                result.append(SPACE).append(SEPARATOR).append(SPACE)
                        .append(board[i][j] == null ? SPACE : board[i][j]);
            }
            result.append(SPACE).append(SEPARATOR).append(System.lineSeparator());
        }
        return result.append("   ").append("-".repeat(47)).toString();
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
        return round != game.round ||
                !Objects.equals(playerOne, game.playerOne) ||
                !Objects.equals(playerTwo, game.playerTwo) ||
                !Objects.equals(next, game.next) ||
                stateOfGame != game.stateOfGame ||
                !Objects.equals(history, game.history) ||
                Arrays.deepEquals(board, game.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOne, playerTwo, next, stateOfGame, round, history, Arrays.deepHashCode(board));
    }

    @Override
    public abstract Game clone();

    @Override
    public GameState save() {
        return new GameState(getNext(), round, board);
    }

    @Override
    public void restore(GameState save) {
        next = save.next();
        round = save.round();
        board = save.board();
    }

    public record GameState(Player next, int round, Piece[][] board) {
    }

}
