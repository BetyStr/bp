package cz.muni.fi.pb162.project;

/**
 * Enum represents color of  the piece.
 * Ordinal values are {@code WHITE} - 0, {@code BLACK} - 1.
 *
 * @author Alzbeta Strompova
 */
public enum Color {
    WHITE,
    BLACK;

    /**
     * Return opposite color that means
     * {@code WHITE} -> {@code BLACK},
     * {@code BLACK} -> {@code WHITE}.
     *
     * @return {@code BLACK} if color is {@code WHITE} and {@code WHITE} if color is {@code BLACK}
     */
    public Color getOppositeColor() {
        return equals(WHITE) ? BLACK : WHITE;
    }

}
