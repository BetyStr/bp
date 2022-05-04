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
        BasicRulesTester.testInheritance(Playable.class, Game.class);
    }

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Game.class, 4);
        BasicRulesTester.methodsAmount(Game.class, 9);
    }

    @Test
    void testToStringStateOfGame() {
        assertEquals("white_player_win", StateOfGame.WHITE_PLAYER_WIN.toString());
        assertEquals("black_player_win", StateOfGame.BLACK_PLAYER_WIN.toString());
        assertEquals("playing", StateOfGame.PLAYING.toString());
        assertEquals("pat", StateOfGame.PAT.toString());
    }
}
