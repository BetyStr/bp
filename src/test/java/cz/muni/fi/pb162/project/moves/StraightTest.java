package cz.muni.fi.pb162.project.moves;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class StraightTest {

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Straight.class));
    }

    @Test
    void getAllowedMoves() {
    }
}