package cz.muni.fi.pb162.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for data import.
 * Format of header: [playerName]-[playerColor];[playerName]-[playerColor]
 * Format of file (Board.Size x Board.Size):
 * [typeOfPiece],[colorOfPiece];[typeOfPiece],[colorOfPiece];...
 * ...
 **/
public interface GameReadable {

    /**
     * Read Game data from input stream.
     *
     * @param is input stream
     * @return the object with info from the data
     * @throws IOException on read error
     */
    GameReadable read(InputStream is) throws IOException;

    /**
     * Read Game data from input stream.
     *
     * @param is        input stream
     * @param hasHeader is true if steam has hasHeader
     * @return the object with info from the data
     * @throws IOException on read error
     */
    GameReadable read(InputStream is, boolean hasHeader) throws IOException;

    /**
     * Read Game data from input file.
     *
     * @param file input file
     * @return the object with info from the data
     * @throws IOException on read error
     */
    GameReadable read(File file) throws IOException;

    /**
     * Read Game data from input file.
     *
     * @param file input file
     * @return the object with info from the data
     * @throws IOException on read error
     */
    GameReadable read(File file, boolean header) throws IOException;

}
