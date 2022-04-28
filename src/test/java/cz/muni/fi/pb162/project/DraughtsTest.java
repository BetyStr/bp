package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class DraughtsTest {

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Draughts.class, 0);
        BasicRulesTester.methodsAmount(Draughts.class, 5);
    }

    @Test
    void getPlayers() {
        var player = new Player("Pat", Color.BLACK);
        var player2 = new Player("Mat", Color.WHITE);
        var game = new Draughts(player, player2);
        assertEquals(player, game.getPlayerOne());
        assertEquals(player2, game.getPlayerTwo());
    }

    @Test
    void getCurrentPlayer() {
        var player = new Player("Janko", Color.BLACK);
        var player2 = new Player("Matko", Color.WHITE);
        var game = new Draughts(player, player2);
        assertEquals(player2, game.getCurrentPlayer());
        game.getBoard().setRound(1);
        assertEquals(player, game.getCurrentPlayer());
    }

    @Test
    void getAndSetStateOfGame() {
        var game = new Draughts(null, null);
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
    }


//    @Test
//    void putPieceOnBoard() {
//        var game = new Draughts(null, null);
//        var piece = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
//        game.putPieceOnBoard(3, 4, piece);
//        game.putPieceOnBoard(6, 1, piece);
//        game.putPieceOnBoard(2, 2, piece);
//        game.putPieceOnBoard(5, 7, piece);
//
//        assertTrue(game.getBoard().isEmpty(1, 1));
//        assertTrue(game.getBoard().isEmpty(7, 6));
//        assertTrue(game.getBoard().isEmpty(3, -1));
//        assertTrue(game.getBoard().isEmpty(8, 6));
//        assertFalse(game.getBoard().isEmpty(3, 4));
//        assertFalse(game.getBoard().isEmpty(6, 1));
//        assertFalse(game.getBoard().isEmpty(2, 2));
//        assertFalse(game.getBoard().isEmpty(5, 7));
//    }

    @Test
    void play() {
    }

    @Test
    void setInitialSet() {
    }

    @Test
    void move() {
    }

    @Test
    void updateStatus() {
    }
}