package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Buildable;
import cz.muni.fi.pb162.project.enums.and.interfaces.ChessNotation;
import cz.muni.fi.pb162.project.enums.and.interfaces.ChessPieces;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * @author Alzbeta Strompova
 * todo add interface read a write
 * long algebraic notation
 */
public class Game {

    private final static Scanner scanner = new Scanner(System.in);
    private final Player playerOne;
    private final Player playerTwo;
    public Board board; // todo not public
    private Player next;
    private int round;

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

    //todo set private
    public Board setInitialSet() {
        board = new Board();
        board.putPieceOnBoard(4, 0, new ChessPiece(Color.White, 4, 0, ChessPieces.King));
        board.putPieceOnBoard(3, 0, new ChessPiece(Color.White, 3, 0, ChessPieces.Queen));
        board.putPieceOnBoard(0, 0, new ChessPiece(Color.White, 0, 0, ChessPieces.Rook));
        board.putPieceOnBoard(7, 0, new ChessPiece(Color.White, 7, 0, ChessPieces.Rook));
        board.putPieceOnBoard(1, 0, new ChessPiece(Color.White, 1, 0, ChessPieces.Knight));
        board.putPieceOnBoard(6, 0, new ChessPiece(Color.White, 6, 0, ChessPieces.Knight));
        board.putPieceOnBoard(2, 0, new ChessPiece(Color.White, 2, 0, ChessPieces.Bishop));
        board.putPieceOnBoard(5, 0, new ChessPiece(Color.White, 5, 0, ChessPieces.Bishop));

        board.putPieceOnBoard(4, 7, new ChessPiece(Color.Black, 4, 7, ChessPieces.King));
        board.putPieceOnBoard(3, 7, new ChessPiece(Color.Black, 3, 7, ChessPieces.Queen));
        board.putPieceOnBoard(0, 7, new ChessPiece(Color.Black, 0, 7, ChessPieces.Rook));
        board.putPieceOnBoard(7, 7, new ChessPiece(Color.Black, 7, 7, ChessPieces.Rook));
        board.putPieceOnBoard(1, 7, new ChessPiece(Color.Black, 1, 7, ChessPieces.Knight));
        board.putPieceOnBoard(6, 7, new ChessPiece(Color.Black, 6, 7, ChessPieces.Knight));
        board.putPieceOnBoard(2, 7, new ChessPiece(Color.Black, 2, 7, ChessPieces.Bishop));
        board.putPieceOnBoard(5, 7, new ChessPiece(Color.Black, 5, 7, ChessPieces.Bishop));

        for (int i = 0; i < 8; i++) {
            board.putPieceOnBoard(i, 1, new ChessPiece(Color.White, i, 1, ChessPieces.Pawn));
            board.putPieceOnBoard(i, 6, new ChessPiece(Color.Black, i, 6, ChessPieces.Pawn));
        }
        return board;
    }

        // todo
    private Boolean isEnd() {
        // king is dead, one step before
        // not existing move
        // can not win
        // same move again and again
        return false;
    }

    // todo exception
    private void play() throws Exception {
        while (!isEnd()) {
            System.out.println(String.format("Next one is %s", next) + System.lineSeparator());
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            var piece = board.getPiece(fromPosition);

            if (piece == null) {
                throw new RuntimeException("on" + fromPosition + " is not any piece");
            }
            if (!piece.getAllPossibleMoves(board).contains(toPosition)) {
                throw new NotAllowedMoveException(piece.getChessNotation() + "can move to " + toPosition);
            }
            round += 1;
            System.out.println(board.move(fromPosition, toPosition));
            next = next.equals(playerOne) ? playerTwo : playerOne;
            System.out.println(board);
        }
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


    // todo builder first then read
    public void playNewGame() throws Exception {
        board = setInitialSet();
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


}
