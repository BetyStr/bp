package cz.muni.fi.pb162.project;

/**
 * Class represent piece of board game
 *
 * @author Alzbeta Strompova
 */
public class Piece {

    private final long id;

    /**
     * Constructor which set up uniq id.
     */
    public Piece(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
