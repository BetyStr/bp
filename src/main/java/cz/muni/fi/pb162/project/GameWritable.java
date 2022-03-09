package cz.muni.fi.pb162.project;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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
