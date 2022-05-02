## Seventh iteration

1. Create classes `Pawn` and `Knight` implementing `Move` in package `moves`.
   - Method `getAllowedMoves` returns all possible positions for the piece at given `position` in given `game`.
   - All positions to return must be empty or occupied by the piece of opposite color.
   - Class `Knight` (L move) do not have any attribute.
     - It is straight move by two squares, rotation by 90 degrees to one of side and move by one square.
   - Class `Pawn` is straight move only forward by one or two steps if the position is empty. 
     - If piece is not move already (his position is the second in case of white pieces 
       or the seventh column in case of black pieces) then the piece can move by one or two steps forward 
       otherwise only by one step.
     - `Pawn` move included case that is a piece of opposite color one step further diagonally. 
       (In our implementation, we neglect the movement En passant.)
2. Add method `getSymbol` to `PieceType`.
   - The method returns string which contains only one unicode character.
     base on `color` (given as input parameter) and `piecetype`.
   - **Do not use `if` or `switch`**. Classes `Pair` and `Map` can make your implementation easier.
   - Character for all needed combinations of `color` and `piecetype`
     you can find [here](https://www.fileformat.info/info/unicode/index.htm).
3. Update `Piece` class. 
   - Add attribute `moves`. It is list of classes implementing interface `Move`
     which represent all movement that the piece can do.
     Do not forget that we do not want to give a chance to anybody to change the attribute by getter.
   - Add method `getAllPossibleMoves` which takes one input parameter of type `game` 
     and returns a set of all possible coordinates where the piece can move. **Use streams**.
   - Update constructor, methods `makeCopy` and `toString`.
     - Constructor takes another input parameter to set `moves`.
     - Update method `makeCopy` because we have new attribute.
     - Edit `toString` method to returns symbol of piece using `getSymbol` from `PieceType`. 
4. Change creating pieces by using design pattern factory method.
   - Create abstract class `PieceFactory` implementing `FactoryMethodOfPiece`.
     - It has one attribute of type `Map<PieceType, Piece>`. Map contains every `PieceType` which constructor gets 
       and prototype of piece with this type and corresponding moves. 
     - It has protected constructor which takes set of `pieceType` as input parameter and set attribute of class.
     - Method `createSetOfPrototypes` is abstract.
     - Method `createPiece` returns piece with type and color given as input parameters.
   - Create `ChessPieceFactory` and `DraughtsPieceFactory`.
     - Both classes extend `PieceFactory` and have constructor without parameters 
       which give superclass set of `PieceType` who are in the game.
     - If in `createPiece` you get unknown `pieceType` throw `IllegalArgumentException` with appropriate message. 
     - If in `createSetOfPrototypes` you get unknown `pieceType` throw `IllegalArgumentException` 
       with appropriate message.
   - Do not forget to update creating pieces in `setInitialSet` in `Chess` and `Draughts`.
5. Add method `allPossibleMovesByCurrentPlayer` in `Game` which returns set of all possible moves 
   than can do current player. **Use streams**.

Hints:
  - Some IDEs do not have default monospace font for unicode characters. You can set monospace font in setting. 
    Some monospace font supporting unicode characters: 
    DejaVu Sans Mono,  Everson Mono, FreeSerif, Chrysanthi Unicode,...
  - Moves by chess pieces.
    - King can move in any direction by one step.
    - Queen can move in any direction for any steps.
    - Rook can move straight for any steps.
    - Bishop can move diagonal for any steps.
    - Knight can move to L(knight move).
    - Pawn can move one or two steps forward base on his position.
  - Moves by draughts pieces.
    - King can move diagonal by one step and jump.
    - Man can move like King but only forward.
  - Some methods for working with the streams are `map`, `filter` and `flatmap`.
  - If you want to use streams in arrays, the static method `Arrays.stream(...)` could be useful.
  - If you want to know more about the strategy design patter
    you can click on [link](https://refactoring.guru/design-patterns/strategy).
  - If you want to know more about the factory method design patter 
    you can click on [link](https://refactoring.guru/design-patterns/factory-method).
