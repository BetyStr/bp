## Sixth iteration

1. Modify class `Piece`.
    -  Override method `equals`. Two pieces will be the same if they have the same `id`.
2. Modify class `Board`.
    - Override method `equals`. Two boards will be the same if they have **all** attributes same.
    - Make `Board` to implement `Originator`.
    - Add a **private** constructor which takes an integer representing a round and 2-dimensional array of pieces
      as the input parameter. It will be used in `save`.
    - Implement the method from `Originator`.
        - Method `save` that "take snapshot" (create Memento that means the state of class)
          and save it to the caretaker attribute. Notice that deep copy is needed.
        - Method `restore` does step back (undo). It sets the last memorized state, which it gets as the input parameter.
3. Make `Playable` to extend `Caretaker`.
4. Update class `Game`.
    - Override method `equals`. Two games will be the same if they have all attributes same.
    - Add attribute `mementoHistory`. **Choose the type of the collection so that you can easily insert new objects
      and select the last inserted ones.**
    - Implement the method from `Caretaker`.
        - The method `hitSave` save "snapshot" of the `Board`.
        - The method `hitUndo` restore "snapshot" of the `Board` if `mementoHistory` is not empty. Otherwise, do nothing.
    - Update method `play` to `hitSave` and print board every round.
5. Update method `move` in the class `Draughts`. If a piece of the type `DRAUGHTS_MAN` passes through the whole board
   it will change to `DRAUGHTS_KING`.
6. Create package `moves`.
    - In this package based on the pre-prepared interface `Move`(design pattern strategy) create classes `Diagonal`, `Jump`, and `Straight`.
    - All positions to return must be **empty** or in the case of `Diagonal` and `Straight`
      can be **occupied by a piece of the opposite color**.
        - Class `Diagonal` has two attributes.
            - The first argument represents the length of a maximal possible move of the piece
              (for  example, Chess King can only move by one square) with default value size of the board.
            - The second argument represents if the piece can move only forward with the default value false.
            - Do not forget to create constructors.
                - First constructor without parameters.
                - The second constructor takes an integer representing the length of the step.
                - The third constructor takes an integer representing the length of the step and
                  a boolean representing if a piece can move only forward.
            - Method `getAllowedMoves` returns all possible positions for the piece at a given `position` in a given `game`.
              You can use the pre-prepared static method `getDiagonalShift` from interface `Move`.
        - Class `Jump` has one attribute equivalent to `Diagonal`.
            - The argument represents if the piece can move only forward with the default value false.
            - The move `Jump` is a diagonal movement by two squares at once
              when the middle position between the starting and ending positions is occupied by a piece of the opposite color.
            - Do not forget to create constructors.
                - First constructor without parameters.
                - The second constructor takes a boolean representing if a piece can move only forward.
            - Method `getAllowedMoves` returns all possible positions for the piece at a given `position` in a given `game`.
              You can use the pre-prepared static method `getDiagonalShift` from interface `Move`.
        - Class `Straight` has one attribute equivalent to `Diagonal`.
            - The argument represents the length of a maximal possible move of a piece
              (for  example, Chess King can only move by one square) with default value size of the board.
            - Do not forget to create constructors.
                - First constructor without parameters.
                - The second constructor takes an integer representing the length of the step.
            - Method `getAllowedMoves` returns all possible positions for the piece at a given `position` in a given `game`.
              You can use the pre-prepared static method `getDiagonalShift` from interface `Move`.

Hints:
- **Do not forget** to override `hashcode` when you override `equals`.
  > If two objects are equal according to the equals(Object) method,
  > calling the hashCode() method on each of the two objects must produce the same value.
- If you want to know more about the **memento** design pattern, you can read pre-prepared interfaces
  or click on the [link](https://refactoring.guru/design-patterns/memento).
- If you want to know more about the **strategy** design pattern, you can read a pre-prepared interface 
  or click on the [link](https://refactoring.guru/design-patterns/strategy).
- Util class `Arrays` and static method `System.arraycopy` can make your implementation easier.
- Notice that moving only forward has a different direction based on the color.
