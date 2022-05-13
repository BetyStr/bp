## Seventh iteration
This iteration serves to practice inheritance, collections, streams, and basic exceptions.

1. Create classes `Pawn` and `Knight` implementing `Move` in package `moves`.
    - Method `getAllowedMoves` returns **all possible positions** for the piece at given `position` in the given `game`.
    - All return positions must be **empty** or **occupied by** the piece of **opposite color**.
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
    - Add attribute `moves`. It is **list of classes implementing interface** `Move` representing all movements that the piece can do.
      Do not forget that we do not want to give a chance to anybody to change the attribute by getter.
    - Add the method `getAllPossibleMoves`, which takes one input parameter of the type `game` and returns a set of all possible coordinates where the piece can move. **Use streams**.
    - Update constructor, methods `makeCopy` and `toString`.
        - Constructor takes another input parameter to set `moves`.
        - Update method `makeCopy` because we have a new attribute.
        - Edit `toString` method to return a symbol of the piece using `getSymbol` from `PieceType`.
4. **Change creating pieces** by using design pattern **[Factory Method](https://refactoring.guru/design-patterns/factory-method)**.
    - Create abstract class `PieceFactory` implementing `FactoryMethodOfPiece`.
        - It has one attribute of the type `Map<PieceType, Piece>`. Map contains every `PieceType` which constructor gets
          and prototype of a piece with this type and the corresponding moves.
        - It has a protected constructor which takes a set of `pieceType` as an input parameter and set attribute of the class.
        - The method `createSetOfPrototypes` is **abstract**.
        - The method `createPiece` returns a piece with the type and the color given as input parameters.
    - Create `ChessPieceFactory` and `DraughtsPieceFactory`.
        - Both classes extend `PieceFactory` and have constructor without parameters
          which give superclass set of `PieceType` that are in the game.
        - If in the `createPiece` you get unknown `pieceType` throw `IllegalArgumentException` with **appropriate message**.
        - If in the `createSetOfPrototypes` you get unknown `pieceType` throw `IllegalArgumentException`
          with **appropriate message**.
    - Do not forget to update creating pieces in `setInitialSet` in `Chess` and `Draughts`.

   <img src="images/factory7.png" alt="factory7" width="600"/>.

5. Add method `allPossibleMovesByCurrentPlayer` in `Game`, which returns a set of all possible moves
   that the current player can make. **Use streams**.

Hints:
- Some IDEs do not have a default monospace font for Unicode characters. You can set the monospace font in the setting.
  Some monospace fonts supporting Unicode characters:
  DejaVu Sans Mono,  Everson Mono, FreeSerif, Chrysanthi Unicode,...
- **Moves by chess pieces**.
    - King can move in any direction with one step and castling (next iteration).
    - Queen can move in any direction for any number of steps.
    - Rook can move straight for any number of steps.
    - Bishop can move diagonally for any number of steps.
    - Knight can move to L (knight move).
    - Pawn can move one or two steps forward based on his position.
- **Moves by draughts pieces**.
    - King can move diagonally by one step and jump.
    - Man can move like King but only forward.
- Some methods for working with the streams are `map`, `filter`, and `flatmap`.
- If you want to use streams in arrays, the static method `Arrays.stream(...)` could be helpful.


