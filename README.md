## Eighth iteration

1. Make `Coordinates` implements interface `Comparable`.
    - Implement method `compareTo` which compare base on `letterNumber`.
      If coordinates have same letter numbers then compare base on `number`.
      > for example a1 < a6 < c0 < f5
2. Update method `allPossibleMovesByCurrentPlayer` in `Game`.
    - Create **anonymous class** of interface `Comparator<Coordinates>` and override method `compare`
      so that it compares exactly in the reverse order as the method `compare` in `Coordinates`.
    - Return result in order based on this comparator.
3. Add method `isInDanger` in `Chess` which takes two integers representing `Coordinates`
   and `color` by which color we control that position is in danger as input parameters.
    - Method control if the position is in danger by the player with the color.
      If the position is in danger return `true` otherwise `false`.
    - Watch out for the case when you find out if a position is in danger by Pawn.
4. Create class `Castling` implementing `Move` in package `moves`.
    - The method `getAllowedMoves` returns **all possible positions** for the piece at the position in the game.
      Castling has multiple conditions which must be met.
        - Every position between King and Rook must be empty and can not be in danger
          (opponent can not be able to move his piece to that position).
        - King and Rook can not be moved from the start of the game (use `mementoHistory`).
        - Notice that you do not need to control the type of piece
          because you control that from the start of the game the piece at the position does not move.
        - Do not forget that we have **big** and **small** castling.

      <img src="images/castling.jpg" alt="castling" width="600"/>.
5. **Overload** and modify method `getAllPossibleMoves` in `Piece`.
    - The overloaded method also takes a parameter of type boolean that decides if the result contains castling moves.
    - In the original method is the default value of this parameter `false`.
6. Edit the executable class `Main`. Test **time complexity** by `System.currentTimeMillis()`.
    - Create a private static method without a return value that takes one input parameter of type `Collection`.
      In the method add 10 000 random Coordinates to collection.
      After that remove 1 000 random Coordinates from collection.
    - Print the duration of the method when input parameter is `HashSet` and `ArrayList`
      in format `<timeOfHashSet> < <timeOfArrayList>` and on the next line print result of the expression.


<img src="images/move8.png" alt="move8" width="600"/>.

Hints:
- [Rules for castling.](https://en.wikipedia.org/wiki/Castling#Rules)
- In `Main` create constant value for instance of class `Random`.
