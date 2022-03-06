package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.ChessNotation;
import cz.muni.fi.pb162.project.enums.and.interfaces.ChessPieces;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.enums.and.interfaces.GameReadable;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Alzbeta Strompova
 */
public class Chess extends Game {

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

    public Chess(Game target) {
        super(target);
    }


    @Override
    public void setInitialSet() {
        board = new ChessPiece[8][8];
        putPieceOnBoard(4, 0, new ChessPiece(Color.White, ChessPieces.King));
        putPieceOnBoard(3, 0, new ChessPiece(Color.White, ChessPieces.Queen));
        putPieceOnBoard(0, 0, new ChessPiece(Color.White, ChessPieces.Rook));
        putPieceOnBoard(7, 0, new ChessPiece(Color.White, ChessPieces.Rook));
        putPieceOnBoard(1, 0, new ChessPiece(Color.White, ChessPieces.Knight));
        putPieceOnBoard(6, 0, new ChessPiece(Color.White, ChessPieces.Knight));
        putPieceOnBoard(2, 0, new ChessPiece(Color.White, ChessPieces.Bishop));
        putPieceOnBoard(5, 0, new ChessPiece(Color.White, ChessPieces.Bishop));

        putPieceOnBoard(4, 7, new ChessPiece(Color.Black, ChessPieces.King));
        putPieceOnBoard(3, 7, new ChessPiece(Color.Black, ChessPieces.Queen));
        putPieceOnBoard(0, 7, new ChessPiece(Color.Black, ChessPieces.Rook));
        putPieceOnBoard(7, 7, new ChessPiece(Color.Black, ChessPieces.Rook));
        putPieceOnBoard(1, 7, new ChessPiece(Color.Black, ChessPieces.Knight));
        putPieceOnBoard(6, 7, new ChessPiece(Color.Black, ChessPieces.Knight));
        putPieceOnBoard(2, 7, new ChessPiece(Color.Black, ChessPieces.Bishop));
        putPieceOnBoard(5, 7, new ChessPiece(Color.Black, ChessPieces.Bishop));

        for (int i = 0; i < 8; i++) {
            putPieceOnBoard(i, 1, new ChessPiece(Color.White, ChessPieces.Pawn));
            putPieceOnBoard(i, 6, new ChessPiece(Color.Black, ChessPieces.Pawn));
        }
    }


    @Override
    public String move(Coordinates oldPosition, Coordinates newPosition) {
        var fired = getPiece(newPosition);
        var piece = getPiece(oldPosition);
        board[newPosition.getLetterNumber()][newPosition.getNumber()] = board[oldPosition.getLetterNumber()][oldPosition.getNumber()];
        board[oldPosition.getLetterNumber()][oldPosition.getNumber()] = null;
        return ((ChessPiece)piece).getChessNotation() +
                ChessNotation.getNotationOfCoordinates(oldPosition.getLetterNumber(), oldPosition.getNumber()) +
                (fired == null ? "" : "x") +
                ChessNotation.getNotationOfCoordinates(newPosition.getLetterNumber(), newPosition.getNumber());
    }



    private Coordinates getInputFromPlayer() throws Exception {
        var position = scanner.next().trim();
        if (position.length() != 2 ) {
            throw new Exception("");
        }
        var letterNumber = position.charAt(0);
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return ChessNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    // todo exception
    private void play() throws Exception {
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
            nextRound();
            System.out.println(move(fromPosition, toPosition));
            nextTurn();
            System.out.println(printBoardToConsole());
        }
    }

    /// region IO
    //todo IO

    @Override
    public GameReadable read(InputStream is) throws IOException {
        return null;
    }

    @Override
    public GameReadable read(File file) throws IOException {
        return null;
    }

    @Override
    public void write(OutputStream os) throws IOException {

    }

    @Override
    public void write(File file) throws IOException {
    }

    // todo builder first then read
    public void playNewGame() throws Exception {
        setInitialSet();
        play();
    }

    public void playGameFromFile(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        while (br.ready()) {
            // todo nastavit board
//            String[] data = br.readLine().split(" ", 3);
//            if (data.length != 3) {
//                throw new IOException("Invalid data (some information might be missing)");
//            }

        }
        play();
    }

    public void playGameFromFile(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file)) {
            playGameFromFile(fis);
        }
    }

    /// endregion IO

    // todo
    private Boolean isEnd() {
        // king is dead, one step before
        // not existing move
        // can not win
        // same move again and again
        return false;
    }

    @Override
    public Game clone() {
        return new Chess(this);
    }
}
