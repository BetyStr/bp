package cz.muni.fi.pb162.project;

/**
 * Record represents player of board game.
 *
 * @param name  is the name of player.
 * @param color is the color of player.
 * @author Alzbeta Strompova
 */
public record Player(String name, Color color) {

    @Override
    public String toString() {
        return String.format("%s-%s", name, color);
    }

}

