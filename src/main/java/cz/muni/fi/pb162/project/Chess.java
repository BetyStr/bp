package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.MissingPlayerException;
import cz.muni.fi.pb162.project.utils.BoardNotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Class for representing simplification of board game Chess.
 * Subclass of abstract class {@code Game}.
 *
 * @author Alzbeta Strompova
 */
public class Chess extends Game {

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
     * Private constructor because design pattern prototype
     *
     * @param target game to copy
     */
    private Chess(Game target) {
        super(target);
    }

    /**
     * Private constructor because design pattern builder.
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

    private void checkCastling(Coordinate oldPosition, Coordinate newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        if (!piece.getPieceType().equals(PieceType.KING)) {
            return;
        }
        if (Math.abs(oldPosition.letterNumber() - newPosition.letterNumber()) > 1) {
            if (newPosition.letterNumber() == 2) {
                move(new Coordinate(0, oldPosition.number()),
                        new Coordinate(3, oldPosition.number()));
            } else {
                move(new Coordinate(7, oldPosition.number()),
                        new Coordinate(5, oldPosition.number()));
            }
        }
    }

    @Override
    public void move(Coordinate oldPosition, Coordinate newPosition) {
        var piece = getBoard().getPiece(oldPosition);
        checkCastling(oldPosition, newPosition);
        putPieceOnBoard(newPosition.letterNumber(), newPosition.number(), piece);
        putPieceOnBoard(oldPosition.letterNumber(), oldPosition.number(), null);
        // promotion
        if ((newPosition.number() == 0 || newPosition.number() == 7)
                && piece.getPieceType().equals(PieceType.PAWN)) {
            piece.setPieceType(PieceType.QUEEN);
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
                .contains(new Coordinate(letterNumber, number));
        if (emptyPosition) {
            putPieceOnBoard(letterNumber, number, null);
        }
        return value;
    }

    @Override
    public Playable makeClone() {
        return new Chess(this);
    }

    ///region Builder

    /**
     * Creational design pattern that lets you construct complex objects' step by step.
     * The pattern allows you to produce different types and representations
     * of an object using the same construction code.
     * When the object is ready to be built, {@link #build()} method is called.
     *
     * @author Alzbeta Strompova
     */
    public static class Builder implements Buildable<Chess> {

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
    }
    ///endregion Builder
}
