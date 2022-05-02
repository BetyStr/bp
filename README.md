## Ninth iteration
1. Create exceptions `EmptySquareException`, `InvalidFormatOfInputException`, 
   `MissingPlayerException` and `NotAllowedMoveException`.
   - `EmptySquareException` and `NotAllowedMoveException` are checked exceptions.
   - `InvalidFormatOfInputException` and `MissingPlayerException` are unchecked exceptions.
   - In each class create at least two constructors which calls constructor from super class. 
2. Update implementation using exceptions.
   - play 
3. Create a nested utility class `Builder`in `Chess`, that is `Chess.Builder` which implements interface `Buildable`.
   - This class takes care of creating the game. 
   - Method `addPlayer` takes player as an input parameter. On the first call, the first player is added, 
     the second call of this method adds the second player.
   - Method `addPieceToBoard` takes piece and position in chess notation as input parameters. It puts piece on board.
   - Both methods return instance of buildable, so you can chain calls of methods sequentially.
   - The `build` method returns a new `Chess` instance filled with players and board with pieces.
   - Usage:
       ```java
       Chess game = new Chess.Builder()
                .addPlayer(new Player("Mat", Color.WHITE))
                .addPlayer(new Player("Pat", Color.BLACK))
                .addPieceToBoard(new ChessPieceFactory().createPiece(PieceType.KING, Color.WHITE), 'e', 1)
            .build();
       ```
4. Prototype


Hints:
 - If you want to know more about the strategy design patter
   you can click on [link](https://refactoring.guru/design-patterns/builder). 
 - You can use regions to better organize your code.
   ```java
      ///region Buildable 
          code
      ///endregion Buildable
     ```