package cz.muni.fi.pb162.project;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class PieceFactoryTest {

    @Test
    void abstractClass() {
        Class<PieceFactory> clazz = PieceFactory.class;
        Assertions.assertTrue(Modifier.isAbstract(clazz.getModifiers()));
    }

    @Test
    void inheritance() {
        assertTrue(FactoryMethodOfPiece.class.isAssignableFrom(PieceFactory.class));
    }

}