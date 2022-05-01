## Sixth iteration

1. Update class `Piece`. 
   -  Override method `equals`. Two pieces will be same if they have same `id`.
2. Update class `Board`.
   - Override method `equals`. Two boards will be same if they have all attributes same.
   - Make `Board` implement `Originator`.
   - Add private constructor which takes integer representing round and 2-dimensional array of pieces 
     as input parameter. It will be used in `save`.
   - Implement method from `Originator`.
     - Method `save` that "take snapshot" (create Memento that means state of class) 
       and save it to caretaker attribute.
     - Method `restore` do step back(undo). Sets the last memorized state, which he gets as input parameter. 
3. Make `Playeble` extends `Caretaker`.
4. Update class `Game`.
   - Override method `equals`. Two games will be same if they have all attributes same.
   - Add attribute `mementoHistory`. Choose the type of collection so that you can easily insert new objects 
     and select the last inserted ones.
   - Implement method from `Caretaker`.
     - Method `hitSave` save "snapshot" of `Board`.
     - Method `hitUndo` restore "snapshot" of `Board` if `mementoHistory` is not empty. Otherwise, do nothing.
   - Update method `play` to `hitSave` and print board every round. 
5. Update method `move` in class `Draughts`. If piece of type `DRAUGHTS_MAN` passes through the whole board 
   it will change to `DRAUGHTS_KING`.
6. Create package `moves`.
   - In this package base on pre-prepared interface `Move` create classes `Diagonal`, `Jump` and `Straight`.
   - All positions to return must be empty or in case of `Diagonal` and `Straight` 
     can be occupied by the piece of opposite color.
     - Class `Diagonal` has two attributes. 
       - First argument representing length of maximal possible move of piece
         (for  example Chess King can only move by one square) with default value size of board. 
       - Second argument representing if piece can move only forward with default value false.
       - Do not forget to create constructors. 
         - First constructor without parameters.
         - Second constructor takes integer representing length of step.
         - Third constructor takes integer representing length of step and 
           boolean representing if piece can move only forward.
       - Method `getAllowedMoves` returns all possible positions for the piece at given `position` in given `game`. 
         You can use pre-prepared static method `getDiagonalShift` from interface `Move`.
     - Class `Jump` has one attribute equivalent to `Diagonal`.
         - The argument representing if piece can move only forward with default value false. 
         - The move `Jump` is a diagonal movement by two squares at once 
           when the middle position between staring and ending positions is occupied by a piece of opposite color.
         - Do not forget to create constructors.
             - First constructor without parameters.
             - Second constructor takes boolean representing if piece can move only forward.
         - Method `getAllowedMoves` returns all possible positions for the piece at given `position` in given `game`.
           You can use pre-prepared static method `getDiagonalShift` from interface `Move`. 
     - Class `Straight` has one attribute equivalent to `Diagonal`.
         - The argument representing length of maximal possible move of piece
           (for  example Chess King can only move by one square) with default value size of board.
         - Do not forget to create constructors.
             - First constructor without parameters.
             - Second constructor takes integer representing length of step.
         - Method `getAllowedMoves` returns all possible positions for the piece at given `position` in given `game`.
           You can use pre-prepared static method `getDiagonalShift` from interface `Move`.

Hints:
  - Do not forget to override `hashcode` when you override `equals`.
    > If two objects are equal according to the equals(Object) method, 
    > calling the hashCode() method on each of the two objects must produce the same value.
  - If you want to know more about the memento design patter, you can read a pre-prepared interfaces 
    or click on [link](https://refactoring.guru/design-patterns/memento).
  - Util class `Arrays` and static method `System.arraycopy` can make your implementation easier.
  - Notice that move only forward has different direction base on color.