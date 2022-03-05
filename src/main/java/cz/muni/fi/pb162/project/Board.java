package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.enums.and.interfaces.ChessNotation;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;


/**
 * todo memento maybe
 * @author Alzbeta Strompova
 */
public class Board {

    public final static int SIZE = 8;
    public final static char SPACE = '\u2003';
    private final static char SEPARATOR = '|';

    // nie je to moc oop kedze tu nemoze byt piece lebo potrebujem type
    private final ChessPiece[][] board = new ChessPiece[SIZE][SIZE];

    /**
     * @param letterNumber first coordinate to put piece 0-7
     * @param number second coordinate to put piece 0-7
     * @param piece ChessPiece which we want to put on board
     */
    public void putPieceOnBoard(int letterNumber, int number, ChessPiece piece) {
        assert inRange(letterNumber, number);
        board[letterNumber][number] = piece;
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


    public ChessPiece getPiece(Coordinates position) {
        return getPiece(position.getLetterNumber(), position.getNumber());
    }

    public ChessPiece getPiece(int letterNumber, int number) {
        if (isEmpty(letterNumber, number)){
            return null;
        }
        return board[letterNumber][number];
    }

    private boolean inRange(int x, int y) {
        return x <= 7 && y <= 7 && x >= 0 && y >= 0;
    }

    public boolean inRange(Coordinates coordinates) {
        return inRange(coordinates.getLetterNumber(), coordinates.getNumber());
    }

    public boolean isEmpty(Coordinates position) {
        return isEmpty(position.getLetterNumber(), position.getNumber());
    }

    public boolean isEmpty(int x, int y) {
        return inRange(x, y) && board[x][y] == null;
    }

    // todo specificke pre sach treba mozno dat prec
    // plus je taam kusokz notacie sachovej..todo class sachova notacia
    public String move(Coordinates oldPosition, Coordinates newPosition) {
        var fired = getPiece(newPosition);
        var piece = getPiece(oldPosition);
        piece.setLetterNumber(newPosition.getLetterNumber());
        piece.setNumber(newPosition.getNumber());
        board[newPosition.getLetterNumber()][newPosition.getNumber()] = board[oldPosition.getLetterNumber()][oldPosition.getNumber()];
        board[oldPosition.getLetterNumber()][oldPosition.getNumber()] = null;
        // todo vyhodenie ..odstranit tu figurku...maybe? pozor moze byt null
        return piece.getChessNotation() +
                ChessNotation.getNotationOfCoordinates(oldPosition.getLetterNumber(), oldPosition.getNumber()) +
                (fired == null ? "" : "x") +
                ChessNotation.getNotationOfCoordinates(newPosition.getLetterNumber(), newPosition.getNumber());
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        result.append(" ").append(SPACE);
        // numbers
        for (int i = 0; i < board.length; i++) {
            result.append(SPACE).append(SPACE).append(i + 1).append(SPACE);
        }
        result.append(System.lineSeparator());

        for (int i = 0; i < board.length; i++) {
            // board
            result.append("   ").append("-".repeat(47));
            result.append(System.lineSeparator());
            // letters
            result.append((char) (65 + i));
            // pieces
            for (int j = 0; j < board.length; j++) {
                result.append(SPACE).append(SEPARATOR).append(SPACE)
                        .append(board[i][j] == null ? SPACE : board[i][j]);
            }
            result.append(SPACE).append(SEPARATOR).append(System.lineSeparator());
        }
        return result.append("   ").append("-".repeat(47)).toString();
    }

}
