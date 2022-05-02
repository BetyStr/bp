package cz.muni.fi.pb162.project;

/**
 * Functional interface to help implement creational design pattern Prototype
 * that lets you copy existing objects without making your code dependent on their classes.
 *
 * @param <T> what type we want to copy
 * @author Alzbeta Strompova
 */
public interface Prototype<T> {

    /**
     * Method is called when want to make copy.
     *
     * @return copy of object
     */
    T makeClone();
}
