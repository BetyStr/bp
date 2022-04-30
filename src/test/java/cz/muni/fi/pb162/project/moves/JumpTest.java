package cz.muni.fi.pb162.project.moves;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class JumpTest {

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Jump.class));
    }

    @Test
    void getAllowedMoves() {
    }
}