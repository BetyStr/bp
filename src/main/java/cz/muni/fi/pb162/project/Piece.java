package cz.muni.fi.pb162.project;

/**
 * Class represents the piece of the board game.
 *
 * @author Alzbeta Strompova
 */
public class Piece {

    private final long id;

    /**
     * Constructor which set up unique id.
     */
    public Piece(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
