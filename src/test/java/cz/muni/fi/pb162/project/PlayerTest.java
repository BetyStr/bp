package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Alzbeta Strompova
 */
class PlayerTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Player.class, 1);
        BasicRulesTester.methodsAmount(Player.class, 2);
    }

    @Test
    void getAndSetName() {
        var player = new Player();
        assertNull(player.getName());
        player.setName("test");
        assertEquals("test", player.getName());
        player.setName("Martin");
        assertEquals("Martin", player.getName());
        player.setName("");
        assertEquals("", player.getName());
    }

}
