## Fourth iteration

1. Update class `Piece`.
    - Make class to implement interface `Prototype`.
    - Implement method `makeClone`, which returns new piece with the same attributes except attribute `id`. ID will be unique.
    - Override `toString` method to return first letter of `pieceType`.
2. Add to class `Board` methods `getAllPiecesFromBoard`, `getColor(Coordinates)`, `getColor(int, int)` and `getPiece`.
    - The method `getAllPiecesFromBoard` returns an array of all pieces on the board.
    - The method `getColor` returns the color of the piece on the board at the position of the input integers.
      If there is not a piece at the input position or position is out of the board then it returns null.
    - The method `getPiece` is an **overloaded** method, which takes one input parameter of type `Coordinates`.
      Avoid duplicating the code by using an existing implementation.
3. Create **interface** `Playable`. This interface serves as a template so that every game has certain methods
   without which it would not be **playable**.
    - **Interface must be well documented**.
    - Interface will have these methods.
        - The method `setInitialSet` sets the initial layout of the pieces on the board.
        - The method `move` takes two coordinates as input parameters. The method moves the piece from the first position
          to the second position on the board. You can assume that some piece is at the first position.
          Do not forget promotion. If the pawn walks to the last square of the board, it turns into a queen.
        - The method `play` without parameters which will be demonstrating the game. In the loop until the end of the game,
          you will find out which player is next, you get input from the player, increase the round by one, and make a move.
          Don't forget to update `stateOfGame`. The input from the user will consist of a coordinate from which he wants
          to move the piece and a coordinate to which he wants to move the piece.
          For example:
          ```
          a2 a3
          ```
4. **Modify** class `Game`.
    - Make class `Game` to implement interface `Playable`.
    - Implement methods from interface `Playable`.
    - Add method `updateStatus`, which controls if two kings are on the board. If one king is missing, change the status of the game.
      The winner of the game is a player with the king on board.
5. Edit the executable class Main.
    - Create two players with **opposite colors**.
    - Create an instance of the game.
    - Set initial set to game.
    - Make move from `(1,1)` to `(1,2)`
    - Print all pieces from the row, which were moved on the separate lines.


Hints:
- Do not forget `@Override` when you implement a method from the interface.
- [Initial position of chess pieces.](https://en.wikipedia.org/wiki/Rules_of_chess#Initial_setup)
- In `Game` you will need `new Scanner(System.in);`.
