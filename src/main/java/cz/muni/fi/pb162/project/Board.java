package cz.muni.fi.pb162.project;

/**
 * Class representing board for board games.
 *
 * @author Alzbeta Strompova
 */
public class Board {

    public static final int SIZE = 8;
    private final Piece[][] squares = new Piece[SIZE][SIZE];
    private int round;

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

    public void setRound(int round) {
        this.round = round;
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

}
