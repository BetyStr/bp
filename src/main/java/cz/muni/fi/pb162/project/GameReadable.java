package cz.muni.fi.pb162.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for data import.
 * @author Radek Oslejsek, Marek Sabo
 */
public interface GameReadable {

    GameReadable read(InputStream is) throws IOException;

    GameReadable read(File file) throws IOException;

}
