package cz.muni.fi.pb162.project.enums.and.interfaces;

import java.io.*;

/**
 * Interface for data export.
 * <p>
 * Data is in the format <code>x y name</code>.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface GameWritable {

    /**
     * Write the data into the output stream.
     *
     * @param os output stream
     * @throws IOException on write error
     */
    void write(OutputStream os) throws IOException;

    /**
     * Write data to the file.
     *
     * @param file output file
     * @throws IOException on write error
     */
    void write(File file) throws IOException;

// todo look at it
//    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

//    /**
//     * Writes json to the output stream.
//     *
//     * @param os output stream
//     * @throws IOException if IO problem occurs
//     */
//    default void writeJson(OutputStream os, Object object) throws IOException {
//        Writer w = new OutputStreamWriter(os);
//        w.write(GSON.toJson(object));
//        w.flush();
//    }

}
