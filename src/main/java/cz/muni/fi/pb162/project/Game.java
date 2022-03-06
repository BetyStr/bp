package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.enums.and.interfaces.GameReadable;
import cz.muni.fi.pb162.project.enums.and.interfaces.GameWritable;
import cz.muni.fi.pb162.project.enums.and.interfaces.Prototype;


/**
 * @author Alzbeta Strompova
 */
public abstract class Game implements GameReadable, GameWritable, Prototype<Game> {

    private final static char SEPARATOR = '|';
    public final static int SIZE = 8;
    public final static char SPACE = '\u2003';

    private Player playerOne;
    private Player playerTwo;
    private Player next;
    private int round;

    protected Piece[][] board;


    /**
     * Constructor who sets the first player according to the color of the players
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        next = playerOne.color().equals(Color.White) ? playerOne : playerTwo;
    }

    /**
     * Constructor because Prototype
     */
    public Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            next = playerOne.color().equals(Color.White) ? playerOne : playerTwo;
        }
    }



    public Player getNext() {
        return next;
    }

    public void nextTurn() {
        next = next.equals(playerOne) ? playerTwo : playerOne;
    }

    public int getRound() {
        return round;
    }

    public void nextRound() {
        round += 1;
    }

    public Color getColor(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)){
            return null;
        }
        return board[letterNumber][number].getColor();
    }

    public Color getColor(Coordinates position) {
        return getColor(position.getLetterNumber(), position.getNumber());
    }

    public Piece getPiece(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)){
            return null;
        }
        return board[letterNumber][number];
    }

    public Piece getPiece(Coordinates position) {
        return getPiece(position.getLetterNumber(), position.getNumber());
    }

    /**
     * @param letterNumber first coordinate to put piece 0-7
     * @param number second coordinate to put piece 0-7
     * @param piece ChessPiece which we want to put on board
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        assert inRange(letterNumber, number);
        board[letterNumber][number] = piece;
    }

    public abstract void setInitialSet();

    public Coordinates findCoordinatesOfPieceById(long id) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getId() == id) {
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
        return inRange(coordinates.getLetterNumber(), coordinates.getNumber());
    }

    public boolean isEmpty(Coordinates position) {
        return isEmpty(position.getLetterNumber(), position.getNumber());
    }

    public boolean isEmpty(int x, int y) {
        return !inRange(x, y) || board[x][y] == null;
    }

    public abstract String move(Coordinates oldPosition, Coordinates newPosition);

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

}
