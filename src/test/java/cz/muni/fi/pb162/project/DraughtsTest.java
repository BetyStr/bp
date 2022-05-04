package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class DraughtsTest {

    private final Player player = new Player("Pat", Color.BLACK);
    private final Player player2 = new Player("Mat", Color.WHITE);
    private final Game game = new Draughts(player, player2);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Draughts.class, 0);
        BasicRulesTester.methodsAmount(Draughts.class, 3);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Game.class, Draughts.class);
    }

    @Test
    void getPlayers() {
        assertEquals(player, game.getPlayerOne());
        assertEquals(player2, game.getPlayerTwo());
    }

    @Test
    void getCurrentPlayer() {
        assertEquals(player2, game.getCurrentPlayer());
        game.getBoard().setRound(1);
        assertEquals(player, game.getCurrentPlayer());
        game.getBoard().setRound(15);
        assertEquals(player, game.getCurrentPlayer());

        var game2 = new Draughts(player2, player);
        assertEquals(player2, game2.getCurrentPlayer());
        game2.getBoard().setRound(3);
        assertEquals(player, game2.getCurrentPlayer());
        game2.getBoard().setRound(42);
        assertEquals(player2, game2.getCurrentPlayer());
    }

    @Test
    void getAndSetStateOfGame() {
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
    }


    @Test
    void putPieceOnBoard() {
        var piece = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(3, 4, piece);
        game.putPieceOnBoard(6, 1, piece);
        game.putPieceOnBoard(2, 2, piece);
        game.putPieceOnBoard(5, 7, piece);

        assertTrue(game.getBoard().isEmpty(1, 1));
        assertTrue(game.getBoard().isEmpty(7, 6));
        assertTrue(game.getBoard().isEmpty(3, -1));
        assertTrue(game.getBoard().isEmpty(8, 6));
        assertFalse(game.getBoard().isEmpty(3, 4));
        assertFalse(game.getBoard().isEmpty(6, 1));
        assertFalse(game.getBoard().isEmpty(2, 2));
        assertFalse(game.getBoard().isEmpty(5, 7));
    }


    @Test
    void setInitialSet() {
        game.setInitialSet();
        var pieces = game.getBoard().getAllPiecesFromBoard();
        assertEquals(24, pieces.length);
        assertTrue(Arrays.stream(pieces)
                .allMatch(x -> x.getPieceType().equals(PieceType.DRAUGHTS_MAN)));
        IntStream.range(0, 3).forEach(i -> columnStream(i, Color.WHITE));
        IntStream.range(5, 8).forEach(i -> columnStream(i, Color.BLACK));
    }

    private void columnStream(int finalI, Color white) {
        assertTrue(IntStream.range(0, 7)
                        .filter(x -> x % 2 == finalI % 2)
                        .mapToObj(x -> game.getBoard().getPiece(x, finalI))
                        .allMatch(x -> x.getColor().equals(white)),
                "Wrong order of pieces on the board in the " + finalI + ". column");
    }

    @Test
    void move() {
        var piece = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(1, 0, piece);
        game.move(new Coordinates(1, 0), new Coordinates(3, 2));
        assertEquals(piece.getId(), game.getBoard().getPiece(3, 2).getId());
        var piece2 = new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(7, 6, piece2);
        game.move(new Coordinates(7, 6), new Coordinates(3, 2));
        assertEquals(piece2.getId(), game.getBoard().getPiece(3, 2).getId());
    }

    @Test
    void moveWithEjection() {
        var piece = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        var piece2 = new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(3, 3, piece);
        game.putPieceOnBoard(4, 4, piece2);
        game.move(new Coordinates(3, 3), new Coordinates(5, 5));
        assertEquals(piece.getId(), game.getBoard().getPiece(5, 5).getId());
        assertEquals(1, game.getBoard().getAllPiecesFromBoard().length);
        assertNull(game.getBoard().getPiece(4, 4));
    }

    @Test
    void movePromotionWhiteSize() {
        var piece = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(3, 6, piece);
        game.move(new Coordinates(3, 6), new Coordinates(2, 7));
        assertEquals(piece.getColor(), game.getBoard().getColor(2, 7));
        assertEquals(PieceType.DRAUGHTS_KING, game.getBoard().getPiece(2, 7).getPieceType());

        var piece2 = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(7, 4, piece2);
        game.move(new Coordinates(7, 4), new Coordinates(7, 5));
        assertEquals(piece2, game.getBoard().getPiece(7, 5));
        assertEquals(PieceType.DRAUGHTS_MAN, game.getBoard().getPiece(7, 5).getPieceType());
    }

    @Test
    void movePromotionBLackSize() {
        var piece = new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(3, 1, piece);
        game.move(new Coordinates(3, 1), new Coordinates(2, 0));
        assertEquals(piece.getColor(), game.getBoard().getColor(2, 0));
        assertEquals(PieceType.DRAUGHTS_KING, game.getBoard().getPiece(2, 0).getPieceType());

        var piece2 = new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN);
        game.putPieceOnBoard(0, 4, piece2);
        game.move(new Coordinates(0, 4), new Coordinates(0, 5));
        assertEquals(piece2, game.getBoard().getPiece(0, 5));
        assertEquals(PieceType.DRAUGHTS_MAN, game.getBoard().getPiece(0, 5).getPieceType());
    }

    @Test
    void updateStatusEnding() {
        game.putPieceOnBoard(0, 0, new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        game.updateStatus();
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.putPieceOnBoard(0, 0, new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
        game.updateStatus();
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
    }

    @Test
    void updateStatusPlaying() {
        game.setInitialSet();
        game.updateStatus();
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
    }

}