package cz.muni.fi.pb162.project.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Radek Oslejsek, Petr Adamek, Marek Sabo
 */
public class BasicRulesTester {

    public static void attributesFinal(Class clazz) {
        Field[] attributes = BasicRulesTester.getFields(clazz);
        for (Field field : attributes) {
            assertTrue(Modifier.isFinal(field.getModifiers()), "Attributes should be final");
        }
    }

    public static void attributesAmount(Class clazz, int expected) {
        long notConstantAttributes = Arrays.stream(clazz.getDeclaredFields())
                .filter(x -> !isConstant(x.getModifiers()))
                .count();
        Assertions.assertThat(notConstantAttributes)
                .as("Too many non-constant attributes: %s", notConstantAttributes)
                .isLessThanOrEqualTo(expected);
    }

    public static void methodsAmount(Class clazz, int expected) {
        long nonPrivateMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(x -> !isPrivate(x.getModifiers()))
                .count();
        Assertions.assertThat(nonPrivateMethods)
                .as("Too many non-private methods: %s", nonPrivateMethods)
                .isLessThanOrEqualTo(expected);
    }

    /**
     * Tests class inheritance ancestor.
     *
     * @param ancestor     ancestor class
     * @param checkedClass class to be checked
     */
    public static void testAncestor(Class ancestor, Class checkedClass) {
        assertEquals(ancestor, checkedClass.getSuperclass(),
                "Class " + checkedClass + "  should inherit from class " + ancestor);
    }

    private static Field[] getFields(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .toArray(Field[]::new);
    }

    private static boolean isConstant(int mod) {
        return Modifier.isStatic(mod) && Modifier.isFinal(mod);
    }

    private static boolean isPrivate(int mod) {
        return Modifier.isPrivate(mod);
    }

}
