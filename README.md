## Fourth iteration

1. Update class `Piece`.
    - Make class implements interface `Prototype`.
    - Implement method `makeClone`, which return new piece with same attributes except attribute `id`. ID will be unique.
    - Override `toString` method to return first letter of `pieceType`.
2. Add to class `Board` methods `getAllPiecesFromBoard`, `getColor(Coordinates)`, `getColor(int, int)` and `getPiece`.
    - The method `getAllPiecesFromBoard` returns an array of all pieces on board.
    - Methods `getColor` returns the color of the piece on board at the position of these integers.
      If at position is not piece or position is out of board then returns null.
    - The method `getPiece` is an **overloaded** method, which takes one input parameter of type `Coordinates`.
      Avoid duplicating the code by using an existing implementation.
3. Create **interface** `Playable`. This interface serves as a template so that every game has certain methods
   without which it would not be **playable**.
    - **Interface must be well documented**.
    - Interface will these methods.
        - The method `setInitialSet` sets the initial layout of the pieces on the board.
        - The method `move` takes two coordinates as input parameters. The method moves the piece from the first position
          to the second position on board. You can assume that some piece is in the first position.
          Do not forget promotion. If the pawn walks to the last square of the board, he turns into a queen.
        - The method `play` without parameters. Method demonstrating game. In the loop until the end of the game,
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
    - Add method `updateStatus`, which controls if on board are two kings. If one king is missing change the status of the game.
      The winner of the game is a player with the king on board.
5. Edit the executable class Main.
    - Create two players with **opposite colors**.
    - Create an instance of the game.
    - Set initial set to game.
    - Make move from `(1,1)` to `(1,2)`
    - Print all pieces from the row, which were moved on separate lines.


Hints:
- Do not forget `@Override` when you implement a method from the interface.
- [Initial position of chess pieces.](https://en.wikipedia.org/wiki/Rules_of_chess#Initial_setup)
- In `Game` you will need `new Scanner(System.in);`.
