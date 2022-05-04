package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.helper.OutputTester;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing print output of Main class.
 *
 * @author Alzbeta Strompova
 */
class MainTest {

    @Test
    void testMainOutput() {
        assertThat(actualOutput()).doesNotContain(" E |   |");
    }

    private String actualOutput() {
        OutputTester ot = new OutputTester();
        ot.captureOutput();
        Main.main(null);
        ot.releaseOutput();
        return ot.getOutput();
    }

}
