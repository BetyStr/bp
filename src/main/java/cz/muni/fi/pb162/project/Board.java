package cz.muni.fi.pb162.project;

import java.util.Arrays;
import java.util.List;
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
     * Constructor without parameters
     */
    public Board() {
    }

    /**
     * Constructor because design pattern Memento
     *
     * @param round   number of rounds played
     * @param squares 2-dimensional array of Pieces representing board
     */
    private Board(int round, Piece[][] squares) {
        this.round = round;
        this.squares = squares;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Return color of piece at {@code position}.
     *
     * @param letterNumber first coordinate of coordinates from which we want piece
     * @param number second coordinate of coordinates from which we want piece
     * @return color of piece at {@code position}
     */
    public Color getColor(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)) {
            return null;
        }
        return squares[letterNumber][number].getColor();
    }

    /**
     * Return piece at {@code position}.
     *
     * @param letterNumber first coordinate of coordinates from which we want piece
     * @param number second coordinate of coordinates from which we want piece
     * @return piece at {@code position}
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
     * @param position from which we want piece
     * @return piece at {@code position}
     */
    public Piece getPiece(Coordinates position) {
        return getPiece(position.letterNumber(), position.number());
    }

    /**
     * Control if coordinates({@code x}, {@code y}) is in board.
     *
     * @param x first coordinate of coordinates to check
     * @param y second coordinate of coordinates to check
     * @return true if is in board, false if is greater then {@code Board.SIZE} or smaller than zero
     */
    private boolean inRange(int x, int y) {
        return x < SIZE && y < SIZE && x >= 0 && y >= 0;
    }

    /**
     * Control if {@code coordinates} is in board.
     *
     * @param coordinates to check.
     * @return true if is in board, false if is greater then {@code Board.SIZE} or smaller than zero
     */
    public boolean inRange(Coordinates coordinates) {
        return inRange(coordinates.letterNumber(), coordinates.number());
    }

    /**
     * Control if is coordinates ({@code x}, {@code y}) at board empty.
     *
     * @param x first coordinate of coordinates to check
     * @param y second coordinate of coordinates to check
     * @return true if is empty, else id is not empty
     */
    public boolean isEmpty(int x, int y) {
        return !inRange(x, y) || squares[x][y] == null;
    }

    /**
     * Put {@code piece} on board at coordinates ({@code x}, {@code y}).
     *
     * @param letterNumber first coordinate to put piece 0-7
     * @param number       second coordinate to put piece 0-7
     * @param piece        ChessPiece which we want to put on board
     */
    public void putPieceOnBoard(int letterNumber, int number, Piece piece) {
        if (inRange(letterNumber, number)) {
            squares[letterNumber][number] = piece;
        }
    }

    /**
     * Find coordinate of piece by id. Every piece has uniq id.
     * If coordinate with {@code id} does not exist return null.
     *
     * @param id of piece, we want find.
     * @return coordinate of piece with {@code id} or if it does not exist return null.
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
     * Return list of all pieces on board.
     *
     * @return list of all pieces on board.
     */
    public List<Piece> getAllPiecesFromBoard() {
        return Arrays.stream(squares)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .toList();
    }

    ///region Originator
    @Override
    public Board save() {
        return new Board(round, squares);
    }

    @Override
    public void restore(Board save) {
        setRound(save.getRound());
        squares = save.squares;
    }
    ///endregion Originator

    @Override
    public String toString() {
        char space = '\u2003';
        char separator = '|';
        var result = new StringBuilder().append(space).append(space);
        // numbers
        for (int i = 0; i < SIZE; i++) {
            result.append(space).append(space).append(i + 1).append(space);
        }
        result.append(System.lineSeparator());
        for (int i = 0; i < SIZE; i++) {
            // board
            result.append(space).append(space).append("-".repeat(47));
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
        return result.append(space).append(space).append("-".repeat(47)).toString();
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
