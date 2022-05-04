## Ninth iteration
1. Create exceptions `EmptySquareException`, `InvalidFormatOfInputException`,
   `MissingPlayerException` and `NotAllowedMoveException`.
    - `EmptySquareException` and `NotAllowedMoveException` are **checked** exceptions.
    - `InvalidFormatOfInputException` and `MissingPlayerException` are **unchecked** exceptions.
    - In each class create at least **two constructors** which call constructor from the super class.
2. Create a **nested utility class** `Builder` in the `Chess`, that is `Chess.Builder` which implements interface `Buildable`.
    - This class takes care of the creating the game.
    - Method `addPlayer` takes player as an input parameter. On the first call, the first player is added,
      the second call of this method adds the second player.
    - Method `addPieceToBoard` takes piece and position **in chess notation** as an input parameters. It puts piece on the board.
    - Both methods return instance of the buildable, so you can chain calls of methods sequentially.
    - The `build` method returns a new `Chess` instance filled with the players and the board with pieces.
    - Usage:
        ```java
        Chess game = new Chess.Builder()
                 .addPlayer(new Player("Mat", Color.WHITE))
                 .addPlayer(new Player("Pat", Color.BLACK))
                 .addPieceToBoard(new ChessPieceFactory().createPiece(PieceType.KING, Color.WHITE), 'e', 1)
             .build();
        ```
3. **Update implementation using exceptions**.
    - The method `play` in the `Game` throws:
        - `EmptySquareException` if user wants to move a piece from the empty position
          or the position is not in the board.
        - `NotAllowedMoveException` if user wants to do illegal move.
        - `InvalidFormatOfInputException` if user's input is in the wrong format.
            - Input must be `<char><int> <char><int>`.
    - In the class `Builder`, if you call the method `build()`, both players must be already added.
      If not throw an exception `MissingPlayerException`.
    - Do not forget to write meaningful messages to exceptions.
4. Modify interface `Prototype` using **generics**. Replace `Piece` with the generic type.
   - Make `Board`, `Piece` to implement `Prototype` and method `makeCopy`.
      - The method `makeClone` in `Piece` creates a new instance with the same attributes except `id`. 
      - The method `makeClone` in `Board` has similar functionality as the method `save`. 
   - Make `Playable` extends `Prototype`.
     - Create a private constructor in the `Game`, `Chess`, and `Draughts` which takes one input parameter of the type `Game`.
     - In the `Chess` and `Draughts` constructors call constructor from the superclass.
     - In the `Game` constructor set all attributes based on the input parameter.
     

<img src="images/game9.png" alt="game9" width="600"/>.


Hints:
- If you want to know more about the builder design pattern
  you can click on [link](https://refactoring.guru/design-patterns/builder).
- You can use regions to better organize your code.
  ```java
     ///region Buildable 
         code
     ///endregion Buildable
    ```
