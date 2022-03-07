package cz.muni.fi.pb162.project.enums.and.interfaces;


/**
 * This interface represents a builder pattern.
 * @param <T> represents a type which should be built.
 * @author not Alzbeta Strompova
 */
public interface Buildable<T> {

    T build();

}
