package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.MissingPlayerException;
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
import java.util.Collection;
import java.util.stream.Collectors;

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
     * @param board     is 2-dimensional array to represent board of pieces
     */
    private Chess(Player playerOne, Player playerTwo, Board board) {
        super(playerOne, playerTwo);
        this.setBoard(board);
    }

    @Override
    public void setInitialSet() {
        putPieceOnBoard(4, 0, new Piece(Color.WHITE, TypeOfPiece.KING));
        putPieceOnBoard(3, 0, new Piece(Color.WHITE, TypeOfPiece.QUEEN));
        putPieceOnBoard(0, 0, new Piece(Color.WHITE, TypeOfPiece.ROOK));
        putPieceOnBoard(7, 0, new Piece(Color.WHITE, TypeOfPiece.ROOK));
        putPieceOnBoard(1, 0, new Piece(Color.WHITE, TypeOfPiece.KNIGHT));
        putPieceOnBoard(6, 0, new Piece(Color.WHITE, TypeOfPiece.KNIGHT));
        putPieceOnBoard(2, 0, new Piece(Color.WHITE, TypeOfPiece.BISHOP));
        putPieceOnBoard(5, 0, new Piece(Color.WHITE, TypeOfPiece.BISHOP));

        putPieceOnBoard(4, 7, new Piece(Color.BLACK, TypeOfPiece.KING));
        putPieceOnBoard(3, 7, new Piece(Color.BLACK, TypeOfPiece.QUEEN));
        putPieceOnBoard(0, 7, new Piece(Color.BLACK, TypeOfPiece.ROOK));
        putPieceOnBoard(7, 7, new Piece(Color.BLACK, TypeOfPiece.ROOK));
        putPieceOnBoard(1, 7, new Piece(Color.BLACK, TypeOfPiece.KNIGHT));
        putPieceOnBoard(6, 7, new Piece(Color.BLACK, TypeOfPiece.KNIGHT));
        putPieceOnBoard(2, 7, new Piece(Color.BLACK, TypeOfPiece.BISHOP));
        putPieceOnBoard(5, 7, new Piece(Color.BLACK, TypeOfPiece.BISHOP));

        for (int i = 0; i < Board.SIZE; i++) {
            putPieceOnBoard(i, 1, new Piece(Color.WHITE, TypeOfPiece.PAWN));
            putPieceOnBoard(i, 6, new Piece(Color.BLACK, TypeOfPiece.PAWN));
        }
    }

    private void checkCastling(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        if(!piece.getTypeOfPiece().equals(TypeOfPiece.KING)) {
            return;
        }
        int diff = Math.abs(oldPosition.letterNumber() - newPosition.letterNumber());
        if(diff > 1) {
            putPieceOnBoard(oldPosition.letterNumber() + 1, oldPosition.number(),
                    getBoard().getPiece(oldPosition.letterNumber() + diff + 1, oldPosition.number()));
            putPieceOnBoard(oldPosition.letterNumber() + diff + 1, oldPosition.number(), null);
        }

    }

    @Override
    public void move(Coordinates oldPosition, Coordinates newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        checkCastling(oldPosition, newPosition);
        putPieceOnBoard(newPosition.letterNumber(), newPosition.number(), piece);
        putPieceOnBoard(oldPosition.letterNumber(), oldPosition.number(), null);
        // promotion
        if ((newPosition.number() == 0 || newPosition.number() == 7)
                && piece.getTypeOfPiece().equals(TypeOfPiece.PAWN)) {
            piece.setTypeOfPiece(TypeOfPiece.QUEEN);
        }
    }

    @Override
    public void updateStatus() {
        if (allPossibleMovesByCurrentPlayer().isEmpty()) {
            setStateOfGame(StateOfGame.PAT);
        }
        var kings = getBoard()
                .getAllPiecesFromBoard()
                .stream()
                .filter(x -> x.getTypeOfPiece().equals(TypeOfPiece.KING))
                .toList();
        if (kings.size() < 2) {
            setStateOfGame(kings.get(0).getColor().equals(Color.WHITE)
                    ? StateOfGame.BLACK_PLAYER_WIN
                    : StateOfGame.WHITE_PLAYER_WIN);
        }
    }

    /**
     * Method that control if position is danger by color
     * First I put some piece on this position (if she is empty) because Pawn move
     * After test I removed it
     *
     * @param position to check
     * @param color    by which color we control that position is in danger
     * @return true if {@code position} is in danger by anu piece of {@code color}
     */
    public boolean isInDanger(Coordinates position, Color color) {
        var emptyPosition = getBoard().isEmpty(position);
        if (emptyPosition) {
            putPieceOnBoard(position.letterNumber(), position.number(),
                    new Piece(color.getOppositeColor(), TypeOfPiece.QUEEN));
        }
        var value = getBoard().getAllPiecesFromBoard()
                .stream()
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

    public boolean isInDanger(int x, int y, Color color) {
        return isInDanger(new Coordinates(x, y), color);
    }

    ///region Prototype
    @Override
    public Playable makeClone() {
        return new Chess(this);
    }
    ///endregion Prototype

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
                    br.write(piece.getTypeOfPiece() + "," + piece.getColor());
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

    @Override
    public void writeJson(OutputStream os, Object object) throws IOException {
        GameWritable.super.writeJson(os, object);
    }
    ///endregion Writable

    ///region Builder and Readable
    public static class Builder implements Buildable<Chess>, GameReadable {

        private final Board board = new Board();
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
        public GameReadable read(InputStream is) throws IOException {
            return read(is, false);
        }

        @Override
        public GameReadable read(InputStream is, boolean hasHeader) throws IOException {
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
                        var type = TypeOfPiece.valueOf(piece[0]);
                        var color = Color.valueOf(piece[1]);
                        board.putPieceOnBoard(row, i, new Piece(color, type));
                    } catch (IllegalArgumentException ex) {
                        throw new IOException("Invalid data format", ex);
                    }
                }
                row += 1;
            }
            return this;
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
