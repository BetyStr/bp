## Sixth iteration
This iteration introduces equality and basic collections.

1. Modify the class `Piece` so that two pieces are considered identical if they have the same `id`.
2. Use the [Memento](https://refactoring.guru/design-patterns/memento) design pattern to add functionality
   to the game to return the saved state.
    - Make the `Board` implement `Originator` and add a **private** constructor, which takes an integer representing a round 
      and 2-dimensional array of pieces as the input parameter. It will be used in the method `save`.
        - The method `save` "takes snapshot" (creates a Memento, i.e. the state of the class) 
          and saves it to the caretaker attribute. **Notice that a deep copy is needed.**
        - The method `restore` does a step back (recovers previously stored state).
          It sets the last memorized state, which it gets as the input parameter.
    - Make `Playable` extend the `Caretaker` and update the class `Game`.
        - Add attribute `mementoHistory`. **Choose the type of collection so that you can easily insert new objects
          and select the last inserted ones.**
        - Implement the method from `Caretaker`.
            - The method `hitSave` saves a "snapshot" of the `Board`.
            - The method `hitUndo` restores a "snapshot" of the `Board` if `mementoHistory` is not empty. 
              Otherwise, the method does nothing.
3. Modify the class `Board`.
    - Two boards are the same if they have all attributes identical.
4. Update the class `Game`.
    - Two games are the same if they have all attributes identical.
    - Update the method `play` to `hitSave` and print the board every round.
5. Update the method `move` in the the class `Draughts`. If a piece of the type `DRAUGHTS_MAN`
   passes through the whole board, it will change to `DRAUGHTS_KING`.
6. Create package `moves`.
    - In this package, based on the pre-prepared interface `Move`
      (**design pattern [Strategy](https://refactoring.guru/design-patterns/strategy)**)
      create classes `Diagonal`, `Jump`, and `Straight`.
    - All returned positions must be **empty** or in the case of `Diagonal` and `Straight`
      can be **occupied by a piece of the opposite color**.
        - Class `Diagonal` has two attributes.
            - The first argument represents the length of a maximal possible move of the piece
              (for  example, Chess King can only move by one square) with the default value set to the size of the board.
            - The second argument represents whether the piece can only move forward. The default value is false.
            - Do not forget to create constructors:
                - Add the first constructor without parameters.
                - Add a second constructor, that takes an integer representing the length of the step.
                - Add a third constructor, that takes an integer representing the length of the step and
                  a boolean representing whether a piece can only move forward.
            - Method `getAllowedMoves` returns all possible destinations for the piece at a given `position` 
              in a given `game` using the `Diagonal` move. You can use the pre-prepared static method `getDiagonalShift` from the interface `Move`.
        - Class `Jump` has one attribute equivalent to `Diagonal`.
            - The argument represents if the piece can move only forward with the default value set to false.
            - The move `Jump` is a diagonal movement by two squares at once
              when a piece of the opposite color occupies the middle position between the starting and ending positions.
            - Do not forget to create constructors:
                - The first constructor without parameters.
                - The second constructor takes a boolean representing if a piece can move only forward.
            - Method `getAllowedMoves` returns all possible destinations for the piece at a given `position`
              in a given `game` using the `Jump` move. You can use the pre-prepared static method `getDiagonalShift` from interface `Move`.
        - Class `Straight` has one attribute equivalent to `Diagonal`.
            - The argument represents the length of a maximal possible move of a piece
              (for  example, Chess King can only move by one square) with the default value set to the size of the board.
            - Do not forget to create constructors:
                - The first constructor without parameters.
                - The second constructor takes an integer representing the length of the step.
            - Method `getAllowedMoves` returns all possible destinations for the piece at a given `position` 
              in a given `game` using the `Straight` move. You can use the pre-prepared static method `getDiagonalShift` from interface `Move`.

Hints:
- **Do not forget** to override `hashcode` when you override `equals`.
  > If two objects are equal according to the equals(Object) method,
  > calling the hashCode() method on each of the two objects must produce the same value.
- Util class `Arrays` and static method `System.arraycopy` can make your implementation easier.
- Notice that moving only forward has **a different direction** based on the color.
