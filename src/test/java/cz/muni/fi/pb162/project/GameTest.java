package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class GameTest {

    @Test
    void abstractClass() {
        Class<Game> clazz = Game.class;
        Assertions.assertTrue(Modifier.isAbstract(clazz.getModifiers()));
    }

    @Test
    void inheritance() {
        assertTrue(Caretaker.class.isAssignableFrom(Game.class));
        assertTrue(Prototype.class.isAssignableFrom(Game.class));
        assertTrue(Playable.class.isAssignableFrom(Game.class));
        assertTrue(Caretaker.class.isAssignableFrom(Playable.class));
        assertTrue(Prototype.class.isAssignableFrom(Playable.class));
    }

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Game.class, 5);
        BasicRulesTester.methodsAmount(Game.class, 15);
    }
}