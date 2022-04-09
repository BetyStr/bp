package cz.muni.fi.pb162.project;

/**
 * Enum represent color of piece
 * ordinal: {@code WHITE} - 0, {@code BLACK} - 1
 *
 * @author Alzbeta Strompova
 */
public enum Color {
    WHITE,
    BLACK;

    /**
     * Return opposite color
     * {@code WHITE} -> {@code BLACK},
     * {@code BLACK} -> {@code WHITE}
     *
     * @return {@code BLACK} if color is {@code WHITE} and {@code WHITE} if color is {@code BLACK}
     */
    public Color getOppositeColor() {
        return equals(WHITE) ? BLACK : WHITE;
    }

}
