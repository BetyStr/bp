## Seventh iteration
In this iteration, you will practice working with inheritance, collections, streams, and basic exceptions.

1. Create classes `Pawn` and `Knight` implementing `Move` in the package `moves`.
    - Method `getAllowedMoves` returns **all possible positions** for the piece at a given `position` in the given `game`.
    - All return positions must be **empty** or **occupied by** a piece of the **opposite color**.
    - Class `Knight` (L move) does not have any attribute.
        - It is a straight move by two squares, rotation by 90 degrees to one side, and moving by one square.
    - Class `Pawn` is a straight move only forward by one or two steps if the position is empty.
        - If a piece is **not** moved already (its position is the second in the case of the white pieces
          or the seventh column in the case of the black pieces), then the piece can move by one or two steps forward
          otherwise, only by one step.
        - `Pawn` move includes a case where is a piece of the opposite color one step further diagonally.
          (In our implementation, we neglect the movement En passant.)
2. Add method `getSymbol` to `PieceType`.
    - The method returns a string that contains only one Unicode character.
      based on the `color` (given as an input parameter) and `piecetype`.
    - **Do not use `if` or `switch`**. Classes `Pair` and `Map` can make your implementation easier.
    - You can find character for all needed combinations of `color` and `piecetype`
      [here](https://www.fileformat.info/info/unicode/index.htm).
3. Modify the `Piece` class.
    - Add an attribute `moves`. It is a **list of classes implementing interface** `Move` representing all movements that the piece can do.
      Do not forget that we do not want to give a chance to anybody to change the attribute by getter.
    - Add the method `getAllPossibleMoves`, which takes one input parameter of the type `game` and returns a set of all possible coordinates where the piece can move. **Use streams**.
    - Update the constructor, methods `makeCopy` and `toString`.
        - The constructor takes another input parameter to set `moves`.
        - Update method `makeCopy` because we have a new attribute.
        - Edit `toString` method to return a symbol of the piece using `getSymbol` from `PieceType`.
4. **Change creating pieces** by using the design pattern **[Factory Method](https://refactoring.guru/design-patterns/factory-method)**.
    - Create an abstract class `PieceFactory` implementing `FactoryMethodOfPiece`.
        - It has one attribute of the type `Map<PieceType, Piece>`. Map contains every `PieceType` which constructor gets
          and prototype of a piece with this type and the corresponding moves.
        - It has a protected constructor which takes a set of `pieceType` as an input parameter and set attribute of the class.
        - The method `createSetOfPrototypes` is **abstract**.
        - The method `createPiece` returns a piece with the type and the color given as input parameters.
    - Create `ChessPieceFactory` and `DraughtsPieceFactory`.
        - Both classes extend `PieceFactory` and have constructors without parameters
          which give a superclass set of `PieceType` that are in the game.
        - If in the `createPiece` you get an unknown `pieceType` throw `IllegalArgumentException` with **appropriate message**.
        - If in the `createSetOfPrototypes` you get an unknown `pieceType` throw `IllegalArgumentException`
          with **appropriate message**.
    - Do not forget to update creating pieces in `setInitialSet` in `Chess` and `Draughts`.

   <img src="images/factory7.png" alt="factory7" width="600"/>.

5. Add a method `allPossibleMovesByCurrentPlayer` in `Game`, which returns a set of all possible moves
   that the current player can make. **Use streams**.

Hints:
- Some IDEs do not have a default monospace font for Unicode characters. You can set the monospace font in the setting.
  Some monospace fonts supporting Unicode characters:
  DejaVu Sans Mono,  Everson Mono, FreeSerif, Chrysanthi Unicode,...
- **Moves by chess pieces**.
    - The king can move in any direction with one step and castling (next iteration).
    - The queen can move in any direction for any number of steps.
    - A rook can move straight for any number of steps.
    - A bishop can move diagonally for any number of steps.
    - A knight can move to L (knight move).
    - A pawn can move one or two steps forward based on his position.
- **Moves by draughts pieces**.
    - The king can move diagonally by one step and jump.
    - A man can move like the king but only in the forward direction.
- Some methods for working with the streams are `map`, `filter`, and `flatmap`.
- If you want to use streams in arrays, the static method `Arrays.stream(...)` could be helpful.


