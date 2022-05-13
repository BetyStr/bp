## Eighth iteration
This iteration serves to practice ordered collections and anonymous classes.

1. Define the **natural order** on the `Coordinates` class according to the `equals` method, i.e., it is sorted according to the `letterNumber` in ascending order, and in case of a match, it is sorted by `number` in ascending order.
   > for example a1 < a6 < c0 < f5
2. Update method `allPossibleMovesByCurrentPlayer` in the `Game`.
   - Create **anonymous class** of the interface `Comparator<Coordinates>` and override method `compare`
     so it compares precisely in the reverse order as natural order on the `Coordinates`.
   - Return result in the order based on this comparator.
3. Add method `isInDanger` in the `Chess`, which takes two integers representing `Coordinates`
   and `color` as input parameters. By color, we control what position is in danger.
   - Method controls if the position is in danger by the player with the input color.
     If the position is in danger, return `true` otherwise `false`.
   - **Watch out** for the case when you find out if a position is in danger by Pawn.
4. Create class `Castling` implementing `Move` in package `moves`.
   - The method `getAllowedMoves` returns **all possible positions** for the piece at the position in the game.
     Castling has multiple conditions which must be met.
      - Every position between King and Rook must be empty and can not be in danger
        (opponent can not be able to move his piece to that position).
      - King and Rook **can not be moved** from the start of the game (use `mementoHistory`).
      - Notice that you **do not** need to control the type of piece
        because you control that the piece at the position does not move from the start of the game.
      - Do not forget that we have **big** and **small** castling.

     <img src="images/castling.jpg" alt="castling" width="600"/>.
5. **Overload** and modify method `getAllPossibleMoves` in the `Piece`.
   - The overloaded method also takes a parameter of the type boolean that decides if the result contains castling moves.
   - In the original method, the default value of this parameter is `false`.
6. Edit the executable class `Main`. Test **time complexity** by `System.currentTimeMillis()`.
   - Create a private static method without a return value that takes one input parameter of the type `Collection`.
     In the method add 10 000 random Coordinates to the collection.
     After that remove 1 000 random Coordinates from the collection.
   - Print the duration of the method when input parameter is `HashSet` and `ArrayList`
     in the format `<timeOfHashSet> < <timeOfArrayList>` and on the next line print result of the expression.


<img src="images/move8.png" alt="move8" width="600"/>.

Hints:
- [Rules for castling.](https://en.wikipedia.org/wiki/Castling#Rules)
- In `Main` create a constant value for an instance of the class `Random`.


