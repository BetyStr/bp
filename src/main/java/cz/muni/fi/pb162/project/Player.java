package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Color;

/**
 * record =>
 * constructor with all parameters
 * get and set methods but without "get" or "set"
 * toString, equals, hashCode
 * @author Alzbeta Strompova
 */
public record Player(String name, Color color) {
}

