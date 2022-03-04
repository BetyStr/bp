package cz.muni.fi.pb162.project.enums.and.interfaces;

/**
 * @author Alzbeta Strompova
 */
public enum Color {
    White, Black;

    /**
     * @return Black if color is White and White when color is Black
     */
    public Color getOppositeColor(){
        return equals(White) ? Black : White;
    }
}
