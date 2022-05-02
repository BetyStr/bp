package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.helper.OutputTester;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing print output of Demo class.
 *
 * @author Alzbeta Strompova
 */
class MainTest {

    private static final String EXPECTED_OUTPUT =
            """
                        1   2   3   4   5   6   7   8\s
                      --------------------------------
                    A | ♖ | ♙ |   |   |   |   | ♟ | ♜ |
                      --------------------------------
                    B | ♘ | ♙ |   |   |   |   | ♟ | ♞ |
                      --------------------------------
                    C | ♗ | ♙ |   |   |   |   | ♟ | ♝ |
                      --------------------------------
                    D | ♕ | ♙ |   |   |   | ♟ |   | ♛ |
                      --------------------------------
                    E | ♔ | ♙ |   |   |   |   | ♟ | ♚ |
                      --------------------------------
                    F | ♗ | ♙ |   |   |   |   | ♟ | ♝ |
                      --------------------------------
                    G | ♘ | ♙ |   |   |   |   | ♟ | ♞ |
                      --------------------------------
                    H | ♖ | ♙ |   |   |   |   | ♟ | ♜ |
                      --------------------------------
                        1   2   3   4   5   6   7   8\s
                      --------------------------------
                    A | ♖ | ♙ |   |   |   |   | ♟ | ♜ |
                      --------------------------------
                    B | ♘ | ♙ |   |   |   |   | ♟ | ♞ |
                      --------------------------------
                    C | ♗ | ♙ |   |   |   |   | ♟ | ♝ |
                      --------------------------------
                    D | ♕ | ♙ |   |   |   |   | ♟ | ♛ |
                      --------------------------------
                    E | ♔ | ♙ |   |   |   |   | ♟ | ♚ |
                      --------------------------------
                    F | ♗ | ♙ |   |   |   |   | ♟ | ♝ |
                      --------------------------------
                    G | ♘ | ♙ |   |   |   |   | ♟ | ♞ |
                      --------------------------------
                    H | ♖ | ♙ |   |   |   |   | ♟ | ♜ |
                      --------------------------------
                    """.replace("\n", System.lineSeparator());

    @Test
    void testMainOutput() {
        assertThat(actualOutput()).isEqualTo(EXPECTED_OUTPUT);
    }

    private String actualOutput() {
        OutputTester ot = new OutputTester();
        ot.captureOutput();
        Main.main(null);
        ot.releaseOutput();
        return ot.getOutput();
    }

}
