package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.helper.OutputTester;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing print output of Main class.
 *
 * @author Alzbeta Strompova
 */
class MainTest {

    private static final String EXPECTED_OUTPUT =
            """
                    K
                    null
                    P
                    null
                    null
                    null
                    P
                    K
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
