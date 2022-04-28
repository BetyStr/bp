package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class PlayerTest {

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Player.class, 2);
        BasicRulesTester.methodsAmount(Player.class, 5);
    }

    @Test
    void getters() {
        var player = new Player("janko", Color.WHITE);
        assertEquals("janko", player.name());
        assertEquals(Color.WHITE, player.color());
        var player2 = new Player("Matko", Color.BLACK);
        assertEquals("Matko", player2.name());
        assertEquals(Color.BLACK, player2.color());
    }

    @Test
    void testToString() {
        assertEquals("janko-WHITE", new Player("janko", Color.WHITE).toString());
        assertEquals("Matko-BLACK", new Player("Matko", Color.BLACK).toString());
    }
}
