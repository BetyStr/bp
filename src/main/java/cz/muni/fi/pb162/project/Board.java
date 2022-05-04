package cz.muni.fi.pb162.project;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class representing board for board games.
 *
 * @author Alzbeta Strompova
 */
public class Board implements Originator<Board> {

    public static final int SIZE = 8;
    private Piece[][] squares = new Piece[SIZE][SIZE];
    private int round;

    /**
     * Constructor without parameters.
     */
    public Board() {
    }

    /**
     * Constructor because design pattern Memento.
     *
     * @param round   number of rounds played.
     * @param squares 2-dimensional array of Pieces representing board.
     */
    private Board(int round, Piece[][] squares) {
        this.round = round;
        this.squares = squares;
    }

    /**
     * Control if coordinates({@code x}, {@code y}) is in board.
     *
     * @param x first coordinate of coordinates to check.
     * @param y second coordinate of coordinates to check.
     * @return true if is in board, false if is greater then {@code Board.SIZE} or smaller than zero.
     */
    private static boolean inRange(int x, int y) {
        return x < SIZE && y < SIZE && x >= 0 && y >= 0;
    }

    /**
     * Control if {@code coordinate} is in the board.
     *
     * @param coordinate to check.
     * @return true if is in board, false if is greater then {@code Board.SIZE} or smaller than zero.
     */
    public static boolean inRange(Coordinates coordinate) {
        return inRange(coordinate.letterNumber(), coordinate.number());
    }

    public int getRound() {
        return round;
    }

    /**
     * Set round to the Board. If {@code round} is negative then throw {@code IllegalArgumentException}.
     *
     * @param round int. If {@code round} is negative then throw {@code IllegalArgumentException}.
     */
    public void setRound(int round) {
        if (round < 0) {
            throw new IllegalArgumentException("Round can not be negative.");
        }
        this.round = round;
    }

    /**
     * Return color of piece at {@code position}.
     *
     * @param letterNumber first part of coordinate from which we want the color of piece.
     * @param number       second part of coordinate from which we want the color of piece.
     * @return color of piece at {@code position}.
     */
    public Color getColor(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)) {
            return null;
        }
        return squares[letterNumber][number].getColor();
    }

    /**
     * Return color of piece at {@code position}.
     *
     * @param coordinates from which we want the color of piece.
     * @return color of piece at {@code position}.
     */
    public Color getColor(Coordinates coordinates) {
        return getColor(coordinates.letterNumber(), coordinates.number());
    }

    /**
     * Return piece at {@code position}.
     *
     * @param letterNumber first part of coordinate from which we want piece.
     * @param number       second part of coordinate from which we want piece.
     * @return piece at {@code position}.
     */
    public Piece getPiece(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)) {
            return null;
        }
        return squares[letterNumber][number];
    }

    /**
     * Return piece at {@code position}.
     *
     * @param position from which we want piece.
     * @return piece at {@code position}.
     */
    public Piece getPiece(Coordinates position) {
        return getPiece(position.letterNumber(), position.number());
    }

    /**
     * Control if is coordinate ({@code x}, {@code y}) at board empty.
     *
     * @param x first part of coordinate to check.
     * @param y second part of coordinate to check.
     * @return true if is empty, else id is not empty.
     */
    public boolean isEmpty(int x, int y) {
        return !inRange(x, y) || squares[x][y] == null;
    }

    /**
     * Put {@code piece} on board at coordinate ({@code x}, {@code y}).
     *
     * @param letterNumber part of coordinates to put piece 0-7.
     * @param number       part of coordinates to put piece 0-7.
     * @param piece        which we want to put on board.
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        if (inRange(letterNumber, number)) {
            squares[letterNumber][number] = piece;
        }
    }

    /**
     * Find coordinates of piece by id. Every piece has unique {@code id}.
     * If coordinates with {@code id} do not exist then return null.
     *
     * @param id of the piece, we want to find.
     * @return coordinates of the piece with {@code id} or if it do not exist return null.
     */
    public Coordinates findCoordinatesOfPieceById(long id) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (squares[i][j] != null && squares[i][j].getId() == id) {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Return array of all pieces on board.
     *
     * @return array of all pieces on board.
     */
    public Piece[] getAllPiecesFromBoard() {
        return Arrays.stream(squares)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .toArray(Piece[]::new);
    }

    ///region Originator
    @Override
    public Board save() {
        var squaresCopy = new Piece[squares.length][];
        for (int i = 0; i < squares.length; i++) {
            squaresCopy[i] = new Piece[squares.length];
            System.arraycopy(squares[i], 0, squaresCopy[i], 0, squares.length);
        }
        return new Board(round, squaresCopy);
    }

    @Override
    public void restore(Board save) {
        setRound(save.getRound());
        squares = save.squares;
    }
    ///endregion Originator

    @Override
    public String toString() {
        var space = " ";
        char separator = '|';
        var result = new StringBuilder().append(space).append(space);
        // numbers
        for (int i = 0; i < SIZE; i++) {
            result.append(space).append(space).append(i + 1).append(space);
        }
        result.append(System.lineSeparator());
        for (int i = 0; i < SIZE; i++) {
            // board
            result.append(space).append(space).append("-".repeat(4 * SIZE));
            result.append(System.lineSeparator());
            // letters
            result.append((char) (65 + i));
            // pieces
            for (int j = 0; j < SIZE; j++) {
                result.append(space).append(separator).append(space)
                        .append(squares[i][j] == null ? space : squares[i][j]);
            }
            result.append(space).append(separator).append(System.lineSeparator());
        }
        return result.append(space).append(space).append("-".repeat(4 * SIZE)).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return round == board.round && Arrays.deepEquals(squares, board.squares);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(round);
        result = 31 * result + Arrays.deepHashCode(squares);
        return result;
    }

}
