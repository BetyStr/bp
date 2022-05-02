package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class DraughtsTest {

    private static final PieceFactory FACTORY = new DraughtsPieceFactory();
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
        assertTrue(Game.class.isAssignableFrom(Draughts.class));
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
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE);
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
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE);
        game.putPieceOnBoard(1, 0, piece);
        game.move(new Coordinate(1, 0), new Coordinate(3, 2));
        assertEquals(piece.getId(), game.getBoard().getPiece(3, 2).getId());
        var piece2 = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK);
        game.putPieceOnBoard(7, 6, piece2);
        game.move(new Coordinate(7, 6), new Coordinate(3, 2));
        assertEquals(piece2.getId(), game.getBoard().getPiece(3, 2).getId());
    }

    @Test
    void moveWithEjection() {
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE);
        var piece2 = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK);
        game.putPieceOnBoard(3, 3, piece);
        game.putPieceOnBoard(4, 4, piece2);
        game.move(new Coordinate(3, 3), new Coordinate(5, 5));
        assertEquals(piece.getId(), game.getBoard().getPiece(5, 5).getId());
        assertEquals(1, game.getBoard().getAllPiecesFromBoard().length);
        assertNull(game.getBoard().getPiece(4, 4));
    }

    @Test
    void movePromotionWhiteSize() {
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE);
        game.putPieceOnBoard(3, 6, piece);
        game.move(new Coordinate(3, 6), new Coordinate(2, 7));
        assertEquals(piece, game.getBoard().getPiece(2, 7));
        assertEquals(PieceType.DRAUGHTS_KING, game.getBoard().getPiece(2, 7).getPieceType());

        var piece2 = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE);
        game.putPieceOnBoard(7, 4, piece2);
        game.move(new Coordinate(7, 4), new Coordinate(7, 5));
        assertEquals(piece2, game.getBoard().getPiece(7, 5));
        assertEquals(PieceType.DRAUGHTS_MAN, game.getBoard().getPiece(7, 5).getPieceType());
    }

    @Test
    void movePromotionBLackSize() {
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK);
        game.putPieceOnBoard(3, 1, piece);
        game.move(new Coordinate(3, 1), new Coordinate(2, 0));
        assertEquals(piece, game.getBoard().getPiece(2, 0));
        assertEquals(PieceType.DRAUGHTS_KING, game.getBoard().getPiece(2, 0).getPieceType());

        var piece2 = FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK);
        game.putPieceOnBoard(0, 4, piece2);
        game.move(new Coordinate(0, 4), new Coordinate(0, 5));
        assertEquals(piece2, game.getBoard().getPiece(0, 5));
        assertEquals(PieceType.DRAUGHTS_MAN, game.getBoard().getPiece(0, 5).getPieceType());
    }

    @Test
    void updateStatusEnding() {
        game.putPieceOnBoard(0, 0, FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK));
        game.updateStatus();
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.putPieceOnBoard(0, 0, FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE));
        game.updateStatus();
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
    }

    @Test
    void updateStatusPlaying() {
        game.setInitialSet();
        game.updateStatus();
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
    }

    @Test
    void testEquals() {
        assertThat(new Draughts(null, null)).isEqualTo(new Draughts(null, null));
        assertThat(game).isEqualTo(new Draughts(player, player2));
        var game2 = new Draughts(player, player2);
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE);
        game.putPieceOnBoard(7, 1, piece);
        game2.putPieceOnBoard(7, 1, piece);
        assertThat(game).isEqualTo(game2);
        game.putPieceOnBoard(2, 0, piece);
        game2.putPieceOnBoard(2, 0, piece);
        assertThat(game).isEqualTo(game2);
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertThat(game).isNotEqualTo(game2);
        game.setStateOfGame(StateOfGame.PLAYING);
        game.setInitialSet();
        game2.setInitialSet();
        assertThat(game)
                .withFailMessage("Games have different boards.")
                .isNotEqualTo(game2);
    }

    @Test
    void testHashCode() {
        assertThat(new Draughts(null, null)).hasSameHashCodeAs(new Draughts(null, null));
        assertThat(game).hasSameHashCodeAs(new Draughts(player, player2));
        var game2 = new Draughts(player, player2);
        var piece = FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE);
        game.putPieceOnBoard(7, 1, piece);
        game2.putPieceOnBoard(7, 1, piece);
        assertThat(game).hasSameHashCodeAs(game2);
        game.putPieceOnBoard(2, 0, piece);
        game2.putPieceOnBoard(2, 0, piece);
        assertThat(game).hasSameHashCodeAs(game2);
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertThat(game).doesNotHaveSameHashCodeAs(game2);
        game.setStateOfGame(StateOfGame.PLAYING);
        game.setInitialSet();
        game2.setInitialSet();
        assertThat(game)
                .withFailMessage("Games have different boards.")
                .doesNotHaveSameHashCodeAs(game2);
    }

    @Test
    void caretaker() {
        game.hitSave();
        game.putPieceOnBoard(1, 2, FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK));
        game.hitUndo();
        game.hitUndo(); // nothing should happen
        assertEquals(new Board(), game.getBoard());
        game.putPieceOnBoard(1, 2, FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.BLACK));
        game.hitSave();
        game.putPieceOnBoard(2, 3, FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.BLACK));
        game.hitSave();
        game.putPieceOnBoard(3, 4, FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.BLACK));
        assertEquals(3, game.getBoard().getAllPiecesFromBoard().length);
        game.hitUndo();
        assertEquals(2, game.getBoard().getAllPiecesFromBoard().length);
        assertNull(game.getBoard().getPiece(3, 4));
        game.hitUndo();
        assertEquals(1, game.getBoard().getAllPiecesFromBoard().length);
        assertNull(game.getBoard().getPiece(2, 3));
    }

    @Test
    void allPossibleMovesByCurrentPlayer() {
        Assertions.assertThat(game.allPossibleMovesByCurrentPlayer()).isEmpty();
        game.putPieceOnBoard(0, 0, FACTORY.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE));
        game.putPieceOnBoard(7, 7, FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE));
        game.putPieceOnBoard(5, 5, FACTORY.createPiece(PieceType.DRAUGHTS_KING, Color.BLACK));
        Assertions.assertThat(game.allPossibleMovesByCurrentPlayer())
                .containsOnly(new Coordinate(1, 1),
                        new Coordinate(6, 6));
        game.setInitialSet();
        Assertions.assertThat(game.allPossibleMovesByCurrentPlayer())
                .containsOnly(new Coordinate(1,3),
                        new Coordinate(3,3),
                        new Coordinate(5, 3),
                        new Coordinate(7,3));
        game.getBoard().setRound(1);
        Assertions.assertThat(game.allPossibleMovesByCurrentPlayer())
                .containsOnly(new Coordinate(0,4),
                        new Coordinate(2,4),
                        new Coordinate(4, 4),
                        new Coordinate(6,4));
    }
}