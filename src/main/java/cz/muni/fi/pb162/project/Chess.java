package cz.muni.fi.pb162.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Alzbeta Strompova
 */
public class Chess extends Game implements GameWritable {

    /**
     * Constructor who sets the first player according to the color of the players
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    /**
     * Private constructor because design pattern prototype
     *
     * @param target game to copy
     */
    private Chess(Game target) {
        super(target);
    }

    /**
     * Private constructor because design pattern builder
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     * @param gameBoard     is 2-dimensional array to represent board of pieces
     */
    private Chess(Player playerOne, Player playerTwo, GameBoard gameBoard) {
        super(playerOne, playerTwo);
        this.gameBoard = gameBoard;
    }

    @Override
    public void setInitialSet() {
        gameBoard.board = new Piece[GameBoard.SIZE][GameBoard.SIZE];
        gameBoard.putPieceOnBoard(4, 0, new Piece(Color.White, TypeOfPiece.King));
        gameBoard.putPieceOnBoard(3, 0, new Piece(Color.White, TypeOfPiece.Queen));
        gameBoard.putPieceOnBoard(0, 0, new Piece(Color.White, TypeOfPiece.Rook));
        gameBoard.putPieceOnBoard(7, 0, new Piece(Color.White, TypeOfPiece.Rook));
        gameBoard.putPieceOnBoard(1, 0, new Piece(Color.White, TypeOfPiece.Knight));
        gameBoard.putPieceOnBoard(6, 0, new Piece(Color.White, TypeOfPiece.Knight));
        gameBoard.putPieceOnBoard(2, 0, new Piece(Color.White, TypeOfPiece.Bishop));
        gameBoard.putPieceOnBoard(5, 0, new Piece(Color.White, TypeOfPiece.Bishop));

        gameBoard.putPieceOnBoard(4, 7, new Piece(Color.Black, TypeOfPiece.King));
        gameBoard.putPieceOnBoard(3, 7, new Piece(Color.Black, TypeOfPiece.Queen));
        gameBoard.putPieceOnBoard(0, 7, new Piece(Color.Black, TypeOfPiece.Rook));
        gameBoard.putPieceOnBoard(7, 7, new Piece(Color.Black, TypeOfPiece.Rook));
        gameBoard.putPieceOnBoard(1, 7, new Piece(Color.Black, TypeOfPiece.Knight));
        gameBoard.putPieceOnBoard(6, 7, new Piece(Color.Black, TypeOfPiece.Knight));
        gameBoard.putPieceOnBoard(2, 7, new Piece(Color.Black, TypeOfPiece.Bishop));
        gameBoard.putPieceOnBoard(5, 7, new Piece(Color.Black, TypeOfPiece.Bishop));

        for (int i = 0; i < GameBoard.SIZE; i++) {
            gameBoard.putPieceOnBoard(i, 1, new Piece(Color.White, TypeOfPiece.Pawn));
            gameBoard.putPieceOnBoard(i, 6, new Piece(Color.Black, TypeOfPiece.Pawn));
        }
    }


    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
        var fired = gameBoard.getPiece(newPosition);
        var piece = gameBoard.getPiece(oldPosition);
        gameBoard.board[newPosition.letterNumber()][newPosition.number()] =
                gameBoard.board[oldPosition.letterNumber()][oldPosition.number()];
        gameBoard.board[oldPosition.letterNumber()][oldPosition.number()] = null;
        // dead king -> end of game
        if (fired != null && fired.getType().equals(TypeOfPiece.King)) {
            setStateOfGame(
                    fired.getColor().equals(Color.Black)
                            ? StateOfGame.WhitePlayerWin
                            : StateOfGame.BlackPlayerWin);
        }
        // promotion
        if ((newPosition.number() == 0 || newPosition.number() == 7)
                && piece.getType().equals(TypeOfPiece.Pawn)) {
            piece.setType(TypeOfPiece.Queen);
        }
//        // todo chess notation
//        return piece.getType() +
//                BoardNotation.getNotationOfCoordinates(oldPosition.letterNumber(), oldPosition.number()) +
//                (fired == null ? "" : "x") +
//                BoardNotation.getNotationOfCoordinates(newPosition.letterNumber(), newPosition.number());
    }


    ///region Prototype
    @Override
    public Game clone() {
        return new Chess(this);
    }
    ///endregion Prototype

    ///region Writable
    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        br.write(getPlayerOne() + ";" + getPlayerTwo());
        br.newLine();
        for (int i = 0; i < GameBoard.SIZE; i++) {
            for (int j = 0; j < GameBoard.SIZE; j++) {
                var piece = gameBoard.getPiece(i, j);
                if (piece == null) {
                    br.write("_");
                } else {
                    br.write(piece.getType() + "," + piece.getColor());
                }
                br.write(";");
            }
            br.newLine();
        }
        br.flush();
    }

    @Override
    public void write(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(fos);
        }
    }
    ///endregion Writable

    ///region Builder and Readable
    public static class Builder implements Buildable<Chess>, GameReadable {

        private final GameBoard gameBoard = new GameBoard();
        private Player playerOne;
        private Player playerTwo;

        public Builder addPlayer(Player player) {
            if (playerOne == null) {
                playerOne = player;
            } else if (playerTwo == null) {
                playerTwo = player;
            }
            return this;
        }

        public Builder addPieceToBoard(Piece piece, char letterNumber, int number) {
            var position = BoardNotation.getCoordinatesOfNotation(letterNumber, number);
            gameBoard.board[position.letterNumber()][position.number()] = piece;
            return this;
        }

        @Override
        public Chess build() {
            // todo exception
            if (playerOne == null || playerTwo == null) {
                throw new RuntimeException("todo ");
            }
            return new Chess(playerOne, playerTwo, gameBoard);
        }

        @Override
        public GameReadable read(InputStream is) throws IOException {
            return read(is, false);
        }

        // todo header and exception
        @Override
        public GameReadable read(InputStream is, boolean header) throws IOException {
            Piece[][] board = new Piece[GameBoard.SIZE][GameBoard.SIZE];
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            var count = 0;
            while (br.ready()) {
                String[] data = br.readLine().split(";", GameBoard.SIZE);
                if (data.length != GameBoard.SIZE) {
                    throw new IOException("Invalid data (some information might be missing)");
                }
                for (int i = 0; i < GameBoard.SIZE; i++) {
                    if (!data[i].equals("_")) {
                        String[] piece = data[i].split(",", 2);
                        if (piece.length != 2) {
                            throw new IOException("Invalid data (some information might be missing)");
                        }
                        try {
                            var type = TypeOfPiece.valueOf(piece[0]);
                            var color = Color.valueOf(piece[1]);
                            board[count][i] = new Piece(color, type);
                        } catch (IllegalArgumentException ex) {
                            throw new IOException("Bad input", ex);
                        }
                    }

                }
                count += 1;
            }
            this.gameBoard.board = board;
            return this;
        }

        @Override
        public GameReadable read(File file) throws IOException {
            return read(file, false);
        }

        @Override
        public GameReadable read(File file, boolean header) throws IOException {
            try (FileInputStream fis = new FileInputStream(file)) {
                read(fis, header);
            }
            return this;
        }
    }
    ///endregion Builder and Readable
}
