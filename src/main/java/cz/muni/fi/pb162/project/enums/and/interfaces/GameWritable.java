package cz.muni.fi.pb162.project.enums.and.interfaces;

import java.io.*;

/**
 * Interface for data export.
 */
public interface GameWritable {

    void write(OutputStream os) throws IOException;

    void write(File file) throws IOException;

// todo look at it
//    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

//    default void writeJson(OutputStream os, Object object) throws IOException {
//        Writer w = new OutputStreamWriter(os);
//        w.write(GSON.toJson(object));
//        w.flush();
//    }

}
