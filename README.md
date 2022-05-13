## Fourth iteration
This iteration serves to practice working with user input, overriding methods, and implementing and creating interfaces.


1. Update class `Piece`.
    - Make the class implement interface `Prototype`.
    - Implement method `makeClone`, which returns a new piece with the same attributes except for attribute `id`. 
      The id will be **unique**.
    - Override `toString` method to return first letter of `pieceType`.
2. Add to class `Board` methods `getAllPiecesFromBoard`, `getColor(Coordinates)`, `getColor(int, int)` and `getPiece(Coordinates)`.
    - The method `getAllPiecesFromBoard` returns an array of all pieces on the board.
    - The method `getColor` returns the piece's color on the board at the position of the input integers.
      If there is no piece at the input position or position is out of the board, it returns null.
    - **Avoid code duplication of overloaded methods by calling each other.**
3. Create **interface** `Playable`.
    - **Interface must be well-documented**.
    - The interface introduces methods with the following meaning and signatures:
        - the method `setInitialSet` sets the initial layout of the pieces on the board.
        - the method `move` takes two coordinates as input parameters. The method moves the piece from the first position
          to the second position on the board. You can assume that some piece is in the first position.
          Do not forget promotion. If the pawn walks to the last square of the board, it turns into a queen.
        - the method `play` without parameters will demonstrate the game. In the loop until the end of the game,
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
    - Add method `updateStatus`, which controls whether there are still two kings on the board. If one king is missing, change the status of the game.
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
