## Eighth iteration

1. Update interface `Prototypy`. Use generics(parameterized types). 
   Change signature of method `makeCopy` to return `T`which get interface as parametric type.
2. Make `Playable` extends `Prototype`.
   - Create private constructor in `Game`, `Chess` and `Draughts` which takes one input parameter of type `Game`.
   - In `Chess` and `Draughts` constructor call constructor from super class.
   - In `Game` constructor set all attributes base on input parameter.
3. Make `Coordinate` implements interface `Comparable`.
   - Implement method `compareTo` which compare base on `letterNumber`. 
     If coordinates have same letter numbers then compare base on `number`.
      > for example a1 < a6 < c0 < f5
4. Update method `allPossibleMovesByCurrentPlayer` in `Game`.
   - Create **anonymous class** of interface `Comparator<Coordinate>` and override method `compare` 
     so that it compares exactly in the reverse order as the method `compare` in `Coordinate`.
   - Return result in order base on this comparator.
5. Add method `isInDanger` in `Chess` which takes two integers representing `Coordinate` 
   and `color` by which color we control that position is in danger as input parameters.
   - Method control if the position is in danger by the player with the color. 
     If is in danger return `true` otherwise `false`.
   - Watch out for the case when you find out if a position is in danger by Pawn.
6. Create class Castling implementing Move in package moves.
   - Method getAllowedMoves returns all possible positions for the piece at given position in given game. 
     Castling have multiple conditions which must be met.
     - Every position between King and Rook must be empty and can not be in danger
       (opponent can not be able to move his piece to that position).
     - King and Rook can not be moved from start of the game (use `mementoHistory`).
     - Notice that you do not need to control type of piece 
       because you control that from start of game the piece at the position does not move.
     - Do not forget that we have big and small castling.
   
     <img src="images/castling.jpg" alt="castling" width="600"/>.
7. Overload and update method `getAllPossibleMoves` in `Piece`.
   - Overload method to also takes parameter of type boolean that decides if result contains castling moves.
   - In original method is default value of this parameter `false`. 
8. Edit the executable class `Main`. Test **time complexity** by `System.currentTimeMillis()`.
   - Create private static method without return value which takes one input parameter of type `Collection`. 
     In the method add 10 000 random Coordinates to collection. 
     After that remove 1 000 random Coordinates from collection.
   - Print the duration of the method when input paramter is `HashSet` and `ArrayList` 
     in format `<timeOfHashSet> < <timeOfArrayList>` and on the next line print result of the expression.


Hints:
 - [Rules for castling.](https://en.wikipedia.org/wiki/Castling#Rules)
 - In `Main` create constant value for instance of class `Random`.