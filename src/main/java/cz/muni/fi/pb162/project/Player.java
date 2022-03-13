package cz.muni.fi.pb162.project;

/**
 * Record =>
 * constructor with all attributes,
 * all attributes are final,
 * get methods but without "get" (for example getName() -> name()),
 * intelligent toString, equals, hashCode base on attributes
 *
 * @author Alzbeta Strompova
 */
public record Player(String name, Color color) {

    @Override
    public String toString() {
        return String.format("%s-%s", name, color);
    }
}

