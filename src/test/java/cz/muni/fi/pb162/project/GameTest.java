package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Game.class, 4);
        BasicRulesTester.methodsAmount(Game.class, 9);
    }
}