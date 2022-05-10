package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alzbeta Strompova
 */
class GameTest {

    @Test
    void abstractClass() {
        BasicRulesTester.testAbstractClass(Game.class);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Caretaker.class, Game.class);
        BasicRulesTester.testInheritance(Prototype.class, Game.class);
        BasicRulesTester.testInheritance(Playable.class, Game.class);
        BasicRulesTester.testInheritance(Caretaker.class, Playable.class);
        BasicRulesTester.testInheritance(Prototype.class, Playable.class);
    }

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Game.class, 5);
        BasicRulesTester.methodsAmount(Game.class, 15);
    }

    @Test
    void testToStringStateOfGame() {
        assertEquals("white_player_win", StateOfGame.WHITE_PLAYER_WIN.toString());
        assertEquals("black_player_win", StateOfGame.BLACK_PLAYER_WIN.toString());
        assertEquals("playing", StateOfGame.PLAYING.toString());
        assertEquals("pat", StateOfGame.PAT.toString());
    }

    @Test
    void testEqualsInheritance() {
        var player = new Player("Pat", Color.BLACK);
        var player2 = new Player("Mat", Color.WHITE);
        Chess chess = new Chess(player, player2);
        Game game = (Game) chess.makeClone();
        assertEquals(game, chess);
    }
}
