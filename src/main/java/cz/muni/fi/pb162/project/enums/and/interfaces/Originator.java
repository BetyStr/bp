package cz.muni.fi.pb162.project.enums.and.interfaces;

/**
 * Interface to help implement design pattern Memento
 * @author Alzbeta Strompova
 */
public interface Originator<T> {

    T save();

    void restore(T save);

}
