## Third iteration

This iteration serves to practice work with enumeration type, strings, overriding method `toString()`, and communication between classes.

1. Create an **enumeration type** `PieceType` and define all chess pieces **in the singular**.
2. Create an **enumeration type** `StateOfGame`. It will contain four values representing the possible game state between two players.
   - `WHITE_PLAYER_WIN`, `BLACK_PLAYER_WIN`, `PAT`, `PLAYING`
3. Create an **enumeration type** `Color` and define **two** colors. Add the method `getOppositeColor()`,
   which returns opposite color. \
   White => Black & Black => White.
4. **Modify** `Player` class.
   - Change this class to record.
   - Add attribute `color` of type `Color`.
   - Override `toString`so that it returns string at format `<name>-<color>`.
5. **Modify** `Piece` class.
   - Add attribute `color` of type `Color`.
   - Add attribute `pieceType` of type `pieceType`.
   - Update the constructor to instantiate a piece with user-defined color and piece type. 
     A unique ID is set automatically.
6. **Create** class `Game`.
   - Add attributes `playerOne`, `playerTwo` of type `Player`, 
     `board` of type `Board` and `stateOfGame`of type `StateOfGame`.
      Mark as `final` all attributes that can be final.
   - Add constructor which takes two players as an input parameter.
   - Add getters and setter/s to attributes.
   - Add methods `getCurrentPlayer` and `putPieceOnBoard`.
      - The method `getCurrentPlayer` returns the player who is on the turn, based on the current `round`
        and the `color` of the players. **The white player always starts.**
      - The method `putPieceOnBoard` takes `Piece` as an input parameter and adds it to the `board`.
7. Edit the executable class Main.
   - Create two players with **opposite colors**.
   - Create an instance of `Game` and put `Queen` at position `(1,5)`.
   - Print the type of piece at the same position using `getPiece`.

Hints:
- [Type of chess pieces](https://en.wikipedia.org/wiki/Chess_piece)
- Enumeration type has methods `ordinal()`.

