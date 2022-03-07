package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

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
import java.util.Scanner;

/**
 * todo promotion
 * @author Alzbeta Strompova
 */
public class Chess extends Game implements GameWritable {

    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Constructor who sets the first player according to the color of the players
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    // prototype
    private Chess(Game target) {
        super(target);
    }

    // builder
    private Chess(Player playerOne, Player playerTwo, Piece[][] board) {
        super(playerOne, playerTwo);
        this.board = board;
    }

    @Override
    public void setInitialSet() {
        board = new Piece[SIZE][SIZE];
        putPieceOnBoard(4, 0, new Piece(Color.White, TypeOfPiece.King));
        putPieceOnBoard(3, 0, new Piece(Color.White, TypeOfPiece.Queen));
        putPieceOnBoard(0, 0, new Piece(Color.White, TypeOfPiece.Rook));
        putPieceOnBoard(7, 0, new Piece(Color.White, TypeOfPiece.Rook));
        putPieceOnBoard(1, 0, new Piece(Color.White, TypeOfPiece.Knight));
        putPieceOnBoard(6, 0, new Piece(Color.White, TypeOfPiece.Knight));
        putPieceOnBoard(2, 0, new Piece(Color.White, TypeOfPiece.Bishop));
        putPieceOnBoard(5, 0, new Piece(Color.White, TypeOfPiece.Bishop));

        putPieceOnBoard(4, 7, new Piece(Color.Black, TypeOfPiece.King));
        putPieceOnBoard(3, 7, new Piece(Color.Black, TypeOfPiece.Queen));
        putPieceOnBoard(0, 7, new Piece(Color.Black, TypeOfPiece.Rook));
        putPieceOnBoard(7, 7, new Piece(Color.Black, TypeOfPiece.Rook));
        putPieceOnBoard(1, 7, new Piece(Color.Black, TypeOfPiece.Knight));
        putPieceOnBoard(6, 7, new Piece(Color.Black, TypeOfPiece.Knight));
        putPieceOnBoard(2, 7, new Piece(Color.Black, TypeOfPiece.Bishop));
        putPieceOnBoard(5, 7, new Piece(Color.Black, TypeOfPiece.Bishop));

        for (int i = 0; i < SIZE; i++) {
            putPieceOnBoard(i, 1, new Piece(Color.White, TypeOfPiece.Pawn));
            putPieceOnBoard(i, 6, new Piece(Color.Black, TypeOfPiece.Pawn));
        }
    }


    @Override
    public String move(Coordinates oldPosition, Coordinates newPosition) {
        var fired = getPiece(newPosition);
        var piece = getPiece(oldPosition);
        board[newPosition.letterNumber()][newPosition.number()] = board[oldPosition.letterNumber()][oldPosition.number()];
        board[oldPosition.letterNumber()][oldPosition.number()] = null;
        if (fired != null && fired.getType().equals(TypeOfPiece.King)) {
            setStateOfGame((fired).getColor().equals(Color.Black)
                    ? StateOfGame.WhitePlayerWin
                    : StateOfGame.BlackPlayerWin);
        }
        // todo chess notation
        return ChessNotation.getNotation(piece.getType()) +
                ChessNotation.getNotationOfCoordinates(oldPosition.letterNumber(), oldPosition.number()) +
                (fired == null ? "" : "x") +
                ChessNotation.getNotationOfCoordinates(newPosition.letterNumber(), newPosition.number());
    }

    private Coordinates getInputFromPlayer() {
        var position = scanner.next().trim();
        if (position.length() != 2 ) {
            throw new IllegalArgumentException("");
        }
        var letterNumber = position.charAt(0);
        // todo maybe exception
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return ChessNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    // todo exception
    public void play() throws Exception {
        while (!isEnd()) {
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
            setRound(getRound() + 1);
            System.out.println(move(fromPosition, toPosition));
            nextTurn();
            System.out.println(printBoardToConsole());
            super.history.hitSave();
        }
    }

    // todo end
    private Boolean isEnd() {
        // king is dead, one step before
        // not existing move
        // can not win
        // same move again and again
        return !getStateOfGame().equals(StateOfGame.Playing);
    }

    ///region Prototype
    @Override
    public Game clone() {
        return new Chess(this);
    }
    ///endregion Prototype

    ///region Originator
    @Override
    public GameState save() {
        return new GameState(getNext(), getRound(), board);
    }

    @Override
    public void restore(GameState save) {
        setNext(save.next());
        setRound(save.round());
        board = save.board();
    }
    ///endregion Originator

    ///region Writable
    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        br.write(getPlayerOne() + ";" + getPlayerTwo());
        br.newLine();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                var piece = getPiece(i, j);
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

    ///region Builder
    public static class Builder implements Buildable<Chess>, GameReadable {

        private Piece[][] board = new Piece[SIZE][SIZE];
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
            var position = ChessNotation.getCoordinatesOfNotation(letterNumber, number);
            board[position.letterNumber()][position.number()] = piece;
            return this;
        }

        @Override
        public Chess build() {
            return new Chess(playerOne, playerTwo, board);
        }

        @Override
        public Builder read(InputStream is) throws IOException {
            Piece[][] board = new Piece[SIZE][SIZE];
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            var count = 0;
            while (br.ready()) {
                String[] data = br.readLine().split(";", SIZE);
                if (data.length != SIZE) {
                    throw new IOException("Invalid data (some information might be missing)");
                }
                for (int i = 0; i < SIZE; i++) {
                    if (!data[i].equals("_")) {
                        String[] piece = data[i].split(",", 2);
                        if (piece.length != 2) {
                            throw new IOException("Invalid data (some information might be missing)");
                        }
                        try {
                            var type = TypeOfPiece.valueOf(piece[0]);
                            var color = Color.valueOf(piece[1]);
                            board[count][i] = new Piece(color, type);
                        }  catch (IllegalArgumentException ex) {
                            throw new IOException("Bad input", ex);
                        }
                    }

                }
                count += 1;
            }
            this.board = board;
            return this;
        }

        @Override
        public Builder read(File file) throws IOException {
            try (FileInputStream fis = new FileInputStream(file)) {
                read(fis);
            }
            return this;
        }
    }
    ///endregion Builder
}
