## Third iteration

This iteration aims to practice the work with enumeration types, strings, 
overriding method `toString()`, and the communication between classes.

1. Create an **enumeration type** `PieceType` and define all chess pieces **in the singular**.
2. Create an **enumeration type** `StateOfGame`. It will contain four values 
   representing a possible game state between two players.
   - `WHITE_PLAYER_WIN`, `BLACK_PLAYER_WIN`, `PAT`, `PLAYING`
4. Create an **enumeration type** `Color` and define the **two** colors. Add the method `getOppositeColor()`,
   which returns the opposite color. \
   White => Black & Black => White.
5. Modify the `Player` class.
   - Change this class to **record**.
   - Add the attribute `color` of type `Color`.
   - Override the `toString` method to return the string in the following format: `<name>-<color>`.
6. Modify the `Piece` class.
   - Add the attribute `color` of type `Color`.
   - Add the attribute `pieceType` of type `pieceType`.
   - Update the constructor to instantiate a piece with user-defined color and piece type. 
     A **unique** `id` is set automatically.
7. **Create** a new class `Game`.
   - Add attributes `playerOne`, `playerTwo` of type `Player`, 
     `board` of type `Board`, and `stateOfGame`of type `StateOfGame`.
      **Mark all attributes that can be final with the keyword `final`**.
   - Add a constructor taking two players as the input parameters.
   - Add getters and setter/s for the attributes.
   - Add methods `getCurrentPlayer` and `putPieceOnBoard`.
      - The method `getCurrentPlayer` returns the player whose turn it is, based on the current `round`
        and the `color` of the players. **The white player always starts.**
      - The method `putPieceOnBoard` takes `Piece` as an input parameter and adds it to the `board`.
8. Edit the executable class Main.
   - Create two players with **opposite colors**.
   - Create an instance of `Game` and put `Queen` at position `b6`.
   - Print the type of piece at the same position using `getPiece`.

Hints:
- [Types of chess pieces](https://en.wikipedia.org/wiki/Chess_piece)
- The enumeration type has the method `ordinal()`.

