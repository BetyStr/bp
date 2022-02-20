package cz.muni.fi.pb162.newProject;

/**
 * @author Alzbeta Strompova
 */
public enum Color {
    White, Black;

    public Color getOppositeColor(){
        return equals(White) ? Black : White;
    }
}
