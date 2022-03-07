package cz.muni.fi.pb162.project.enums.and.interfaces;

/**
 * Interface to help implement design pattern Prototype
 * @author Alzbeta Strompova
 */
public interface Prototype<T> {

    T clone();
}
