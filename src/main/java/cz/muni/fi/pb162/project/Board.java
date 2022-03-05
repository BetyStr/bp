package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.enums.and.interfaces.Color;


/**
 * todo memento maybe
 * @author Alzbeta Strompova
 */
public class Board {

    public final static int SIZE = 8;
    public final static char SPACE = '\u2003';
    private final static char SEPARATOR = '|';

    private final ChessPiece[][] board = new ChessPiece[SIZE][SIZE];


    // todo static class chess notation
    /**
     * Chess notation
     * @param x 0-7 => 1-8
     * @param y 0-7 => a-h
     * @return xy example c3
     */
    public static String getNotationOfCoordinates(int x, int y){
        var i = (char)(97 + x);
        return i + String.valueOf(y + 1);
    }

    /**
     * Chess notation REVERSE (wrong javadoc below)
     * @param x 0-7 => 1-8
     * @param y 0-7 => a-h
     * @return xy example c3
     */
    public static Coordinates getCoordinatesOfNotation(char x, int y){
        var i = (int)x - 97;
        return new Coordinates(i,y - 1);
    }

    public void putPieceOnBoard(int letterNumber, int number, ChessPiece piece) {
        board[letterNumber][number] = piece;
    }

    public Color getColor(int letterNumber, int number) {
        if (isEmpty(letterNumber,number)){
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

    public boolean isEmpty(Coordinates position) {
        return inRange(position.getLetterNumber(), position.getNumber()) &&
                board[position.getLetterNumber()][position.getNumber()] == null;
    }

    public boolean isEmpty(int x, int y) {
        return inRange(x, y) && board[x][y] == null;
    }

    // todo specificke pre sach treba mozno dat prec
    // plus je taam kusokz notacie sachovej..todo class sachova notacia
    // todo vyhodenie ..odstranit tu figurku...maybe?
    public String move(Coordinates oldPosition, Coordinates newPosition) {
        var fired = board[newPosition.getLetterNumber()][newPosition.getNumber()];
        var piece = getPiece(oldPosition);
        piece.setLetterNumber(newPosition.getLetterNumber());
        piece.setNumber(newPosition.getNumber());
        board[newPosition.getLetterNumber()][newPosition.getNumber()] = board[oldPosition.getLetterNumber()][oldPosition.getNumber()];
        board[oldPosition.getLetterNumber()][oldPosition.getNumber()] = null;
        return board[newPosition.getLetterNumber()][newPosition.getNumber()].getChessNotation() +
                (fired == null ? "" : "x") +
                Board.getNotationOfCoordinates(newPosition.getLetterNumber(), newPosition.getNumber());
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
