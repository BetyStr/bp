package cz.muni.fi.pb162.project;

/**
 * Interface to help implement design pattern Memento
 *
 * @author Alzbeta Strompova
 */
public interface Originator<T> {

    T save();

    void restore(T save);

}
