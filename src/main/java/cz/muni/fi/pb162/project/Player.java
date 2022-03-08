package cz.muni.fi.pb162.project;

/**
 * record =>
 * constructor with all final parameters
 * get methods but without "get" (for example getName() -> name())
 * toString, equals, hashCode
 *
 * @author Alzbeta Strompova
 */
public record Player(String name, Color color) {

    @Override
    public String toString() {
        return String.format("%s-%s", name, color);
    }
}

