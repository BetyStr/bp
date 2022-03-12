package cz.muni.fi.pb162.project;

/**
 * Enum represent color of piece
 * ordinal: 0 - White, 1 - Black
 *
 * @author Alzbeta Strompova
 */
public enum Color {
    WHITE, BLACK;

    /**
     * Return opposite color
     * White -> Black, Black -> White
     *
     * @return Black if color is White and White if color is Black
     */
    public Color getOppositeColor() {
        return equals(WHITE) ? BLACK : WHITE;
    }

}
