package cz.muni.fi.pb162.project;


/**
 * Interface to help implement a creational design pattern
 * that lets you construct complex objects' step by step.
 * The pattern allows you to produce different types and representations
 * of an object using the same construction code.
 */
public interface Buildable<T> {

    T build();

}
