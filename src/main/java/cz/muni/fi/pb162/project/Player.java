package cz.muni.fi.pb162.project;

/**
 * Record represent player of board game
 *
 * @author Alzbeta Strompova
 */
public record Player(String name, Color color) {

    @Override
    public String toString() {
        return String.format("%s-%s", name, color);
    }
}

