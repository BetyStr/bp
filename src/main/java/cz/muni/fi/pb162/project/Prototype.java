package cz.muni.fi.pb162.project;

/**
 * Interface to help implement creational design pattern Prototype
 * that lets you copy existing objects
 * without making your code dependent on their classes.
 *
 * @author Alzbeta Strompova
 */
public interface Prototype<T> {

    T makeClone();
}
