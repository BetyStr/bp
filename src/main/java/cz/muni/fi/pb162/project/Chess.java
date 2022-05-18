package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.exceptions.MissingPlayerException;
import cz.muni.fi.pb162.project.utils.BoardNotation;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Class for representing simplification of board game Chess.
 * Subclass of abstract class {@code Game}.
 *
 * @author Alzbeta Strompova
 */
public class Chess extends Game implements GameWritable {

    /**
     * Constructor who takes two players with opposite color.
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    /**
     * Private constructor of design pattern prototype.
     *
     * @param target game to copy.
     */
    private Chess(Game target) {
        super(target);
    }

    /**
     * Private constructor of design pattern builder.
     *
     * @param playerOne first of two players needed to play chess.
     * @param playerTwo second of two players needed to play chess.
     * @param board     is 2-dimensional array to represent board of pieces.
     */
    private Chess(Player playerOne, Player playerTwo, Board board) {
        super(playerOne, playerTwo, board);
    }

    @Override
    public void setInitialSet() {
        var factory = new ChessPieceFactory();
        putPieceOnBoard(4, 0, factory.createPiece(PieceType.KING, Color.WHITE));
        putPieceOnBoard(3, 0, factory.createPiece(PieceType.QUEEN, Color.WHITE));
        putPieceOnBoard(0, 0, factory.createPiece(PieceType.ROOK, Color.WHITE));
        putPieceOnBoard(7, 0, factory.createPiece(PieceType.ROOK, Color.WHITE));
        putPieceOnBoard(1, 0, factory.createPiece(PieceType.KNIGHT, Color.WHITE));
        putPieceOnBoard(6, 0, factory.createPiece(PieceType.KNIGHT, Color.WHITE));
        putPieceOnBoard(2, 0, factory.createPiece(PieceType.BISHOP, Color.WHITE));
        putPieceOnBoard(5, 0, factory.createPiece(PieceType.BISHOP, Color.WHITE));

        putPieceOnBoard(4, 7, factory.createPiece(PieceType.KING, Color.BLACK));
        putPieceOnBoard(3, 7, factory.createPiece(PieceType.QUEEN, Color.BLACK));
        putPieceOnBoard(0, 7, factory.createPiece(PieceType.ROOK, Color.BLACK));
        putPieceOnBoard(7, 7, factory.createPiece(PieceType.ROOK, Color.BLACK));
        putPieceOnBoard(1, 7, factory.createPiece(PieceType.KNIGHT, Color.BLACK));
        putPieceOnBoard(6, 7, factory.createPiece(PieceType.KNIGHT, Color.BLACK));
        putPieceOnBoard(2, 7, factory.createPiece(PieceType.BISHOP, Color.BLACK));
        putPieceOnBoard(5, 7, factory.createPiece(PieceType.BISHOP, Color.BLACK));

        for (int i = 0; i < Board.SIZE; i++) {
            putPieceOnBoard(i, 1, factory.createPiece(PieceType.PAWN, Color.WHITE));
            putPieceOnBoard(i, 6, factory.createPiece(PieceType.PAWN, Color.BLACK));
        }
    }

    private void checkCastling(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        if (!piece.getPieceType().equals(PieceType.KING)) {
            return;
        }
        if (Math.abs(oldPosition.letterNumber() - newPosition.letterNumber()) > 1) {
            if (newPosition.letterNumber() == 2) {
                move(new Coordinates(0, oldPosition.number()),
                        new Coordinates(3, oldPosition.number()));
            } else {
                move(new Coordinates(7, oldPosition.number()),
                        new Coordinates(5, oldPosition.number()));
            }
        }
    }

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        checkCastling(oldPosition, newPosition);
        putPieceOnBoard(newPosition.letterNumber(), newPosition.number(), piece);
        putPieceOnBoard(oldPosition.letterNumber(), oldPosition.number(), null);
        //promotion
        if ((newPosition.number() == 0 || newPosition.number() == 7)
                && piece.getPieceType().equals(PieceType.PAWN)) {
            piece.setPieceType(PieceType.QUEEN);
            putPieceOnBoard(newPosition.letterNumber(), newPosition.number(),
                    new ChessPieceFactory().createPiece(PieceType.QUEEN, piece.getColor()));
        }
    }

    @Override
    public void updateStatus() {
        var kings = Arrays.stream(getBoard()
                        .getAllPiecesFromBoard())
                .filter(x -> x.getPieceType().equals(PieceType.KING))
                .toList();
        if (kings.size() < 2) {
            setStateOfGame(kings.get(0).getColor().equals(Color.WHITE)
                    ? StateOfGame.WHITE_PLAYER_WIN
                    : StateOfGame.BLACK_PLAYER_WIN);
        }
    }

    /**
     * Method control if position ({@code x}, {@code y}) is in danger by {@code color}.
     * First I put some piece on this position ({@code x}, {@code y}) (if he is empty) because {@code Pawn}  move.
     * After test, I removed it.
     *
     * @param letterNumber first coordinate of position
     * @param number       second coordinate of position
     * @param color        by which color we control that position is in danger.
     * @return true if position is in danger by anu piece of {@code color}.
     */
    public boolean isInDanger(int letterNumber, int number, Color color) {
        var emptyPosition = getBoard().isEmpty(letterNumber, number);
        if (emptyPosition) {
            putPieceOnBoard(letterNumber, number,
                    new ChessPieceFactory().createPiece(PieceType.QUEEN, color.getOppositeColor()));
        }
        var value = Arrays.stream(getBoard().getAllPiecesFromBoard())
                .filter(q -> q.getColor().equals(color))
                .map(q -> q.getAllPossibleMoves(this, false))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .contains(new Coordinates(letterNumber, number));
        if (emptyPosition) {
            putPieceOnBoard(letterNumber, number, null);
        }
        return value;
    }

    @Override
    public Playable makeClone() {
        return new Chess(this);
    }

    ///region Writable
    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        br.write(getPlayerOne() + ";" + getPlayerTwo());
        br.newLine();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                var piece = getBoard().getPiece(i, j);
                if (piece == null) {
                    br.write("_");
                } else {
                    br.write(piece.getPieceType() + "," + piece.getColor());
                }
                if (j != Board.SIZE - 1) {
                    br.write(";");
                }
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

    /**
     * Creational design pattern that lets you construct complex objects' step by step.
     * The pattern allows you to produce different types and representations
     * of an object using the same construction code.
     * When the object is ready to be built, {@link #build()} method is called.
     *
     * @author Alzbeta Strompova
     */
    public static class Builder implements Buildable<Chess>, GameReadable {

        private final Board board = new Board();
        private Player playerOne;
        private Player playerTwo;

        /**
         * Method that add {@code player}. Can be added exactly two players.
         *
         * @param player we want to add
         * @return buildable with added {@code player} ready for next method.
         */
        public Builder addPlayer(Player player) {
            if (playerOne == null) {
                playerOne = player;
            } else if (playerTwo == null) {
                playerTwo = player;
            }
            return this;
        }

        /**
         * Method that add {@code piece}.
         *
         * @param piece        that we want to add to board
         * @param letterNumber first coordinate when we want to add {@code piece}
         * @param number       second coordinate when we want to add {@code piece}
         * @return buildable with added {@code piece} ready for next method.
         */
        public Builder addPieceToBoard(Piece piece, char letterNumber, int number) {
            var position = BoardNotation.getCoordinatesOfNotation(letterNumber, number);
            board.putPieceOnBoard(position.letterNumber(), position.number(), piece);
            return this;
        }

        @Override
        public Chess build() throws MissingPlayerException {
            if (playerOne == null || playerTwo == null) {
                throw new MissingPlayerException("You must have two players to play");
            }
            return new Chess(playerOne, playerTwo, board);
        }

        @Override
        public Builder read(InputStream is) throws IOException {
            return read(is, false);
        }

        @Override
        public Builder read(InputStream is, boolean hasHeader) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            processHeader(hasHeader, br);
            var row = 0;
            while (br.ready()) {
                String[] data = br.readLine().split(";", Board.SIZE);
                if (data.length != Board.SIZE) {
                    throw new IOException("Invalid data length (some information might be missing)");
                }
                for (int i = 0; i < Board.SIZE; i++) {
                    if (data[i].equals("_")) {
                        continue;
                    }
                    String[] piece = data[i].split(",", 2);
                    if (piece.length != 2) {
                        throw new IOException("Invalid data length (some information might be missing)");
                    }
                    try {
                        convertAndPutBoard(row, i, piece);
                    } catch (IllegalArgumentException ex) {
                        throw new IOException("Invalid data format", ex);
                    }
                }
                row += 1;
            }
            if (row != Board.SIZE) {
                throw new IOException("Invalid data length (some information might be missing)");
            }
            return this;
        }

        private void convertAndPutBoard(int row, int i, String[] piece) {
            var type = PieceType.valueOf(piece[0]);
            var color = Color.valueOf(piece[1]);
            board.putPieceOnBoard(row, i, new ChessPieceFactory().createPiece(type, color));
        }

        private void processHeader(boolean hasHeader, BufferedReader br) throws IOException {
            if (hasHeader) {
                String[] data = br.readLine().split(";", 2);
                var player = data[0].split("-", 2);
                playerOne = new Player(player[0], Color.valueOf(player[1]));
                player = data[1].split("-", 2);
                playerTwo = new Player(player[0], Color.valueOf(player[1]));
            }
        }

        @Override
        public Builder read(File file) throws IOException {
            return read(file, false);
        }

        @Override
        public Builder read(File file, boolean header) throws IOException {
            try (FileInputStream fis = new FileInputStream(file)) {
                read(fis, header);
            }
            return this;
        }
    }
    ///endregion Builder and Readable
}
