package cz.muni.fi.pb162.project.enums.and.interfaces;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for data import.
 * <p>
 * Data is in the format <code>x y name</code>.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface GameReadable {

    /**
     * Read polygon data from input stream.
     *
     * @param is input stream
     * @return the object reading the data
     * @throws IOException on read error
     */
    GameReadable read(InputStream is) throws IOException;

    /**
     * Read polygon data from file.
     *
     * @param file input file
     * @return the object reading the data
     * @throws IOException on read error
     */
    GameReadable read(File file) throws IOException;
}
