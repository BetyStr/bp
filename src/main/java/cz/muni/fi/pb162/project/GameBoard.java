package cz.muni.fi.pb162.project;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alzbeta Strompova
 */
public class GameBoard implements Originator<GameBoard.BoardState> {

    public static final int SIZE = 8;

    protected Piece[][] board;
    private int round;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
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

    /**
     * Method that control if position is danger by color
     * First I put some piece on this position (if she is empty) because Pawn move
     * After test I removed it
     *
     * @param position to check
     * @param color    by which color we control that position is in danger
     * @return true if position is in danger by color
     */
    public boolean isInDanger(Coordinates position, Color color) {
        var emptyPosition = isEmpty(position);
        if (emptyPosition) {
            putPieceOnBoard(position.letterNumber(), position.number(),
                    new Piece(color.getOppositeColor(), TypeOfPiece.Queen));
        }
        var value = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(x -> x.getColor().equals(color))
                .map(x -> x.getAllPossibleMoves(this))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .contains(position);
        if (emptyPosition) {
            putPieceOnBoard(position.letterNumber(), position.number(), null);
        }
        return value;
    }


    public String printBoardToConsole() {
        final char space = '\u2003';
        final char separator = '|';
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
                        .append(board[i][j] == null ? space : board[i][j]);
            }
            result.append(space).append(separator).append(System.lineSeparator());
        }
        return result.append(space).append(space).append("-".repeat(47)).toString();
    }

    @Override
    public BoardState save() {
        return new BoardState(round, board);
    }

    @Override
    public void restore(BoardState save) {
        round = save.round();
        board = save.board();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameBoard gameBoard = (GameBoard) o;
        return round == gameBoard.round && Arrays.deepEquals(board, gameBoard.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(round);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    public record BoardState(int round, Piece[][] board) {
    }
}
