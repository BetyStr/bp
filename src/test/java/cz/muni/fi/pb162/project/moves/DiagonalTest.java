package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Caretaker;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Playable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class DiagonalTest {

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Diagonal.class));
    }

    @Test
    void getAllowedMoves() {
    }
}