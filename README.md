## Fourth iteration
This iteration is focused on practicing the work with the user input, 
overriding methods, and implementing and creating interfaces.


1. Update the class `Piece`.
    - Make the class implement the `Prototype` **interface**
      ([Design pattern Prototype](https://refactoring.guru/design-patterns/prototype)).
    - Implement method `makeClone`, which returns a new piece with the same attributes except for the attribute `id`. 
      The id will be **unique**. 
    - Override the `toString` method to return the first letter of `pieceType`.
2. Add methods `getAllPiecesFromBoard`, `getColor(Coordinates)`, `getColor(int, int)` 
   and `getPiece(Coordinates)` to class `Board`.
    - The method `getAllPiecesFromBoard` returns an array of all pieces on the board.
    - The method `getColor` returns the piece's color on the board at the position of the input integers.
      If there is no piece at the input position or position is out of the board, it returns null.
    - **Avoid code duplication of overloaded methods by calling each other.**
3. Create a new **interface** `Playable`.
    - **The interface must be well-documented**.
    - The interface introduces methods with the following meaning and signatures:
        - the method `setInitialSet` sets the initial layout of the pieces on the board.
        - the method `move` takes two coordinates as input parameters. 
          The method moves the piece from the first position to the second position on the board. 
          You can assume that some piece is in the first position. Do not forget to implement **the promotion**. 
          If the pawn walks to the last square of the board, it turns into a queen.
        - the method `play` without parameters will demonstrate the game. In the loop until the end of the game,
          you will find out which player is next, you get input from the player, increase the round by one, 
          and make a move. Don't forget to update the state of the game. **The input from the user**
          consists of a coordinate from which he wants to move the piece 
          and a coordinate to which he wants to move the piece.
          For example:
          ```
          a2 a3
          ```
4. **Modify** the class `Game`.
    - Make the class `Game` to implement the **interface** `Playable`.
    - Implement methods from the interface `Playable`.
    - Add method `updateStatus`, which controls whether there are still two kings on the board. If one king is missing, 
      change the status of the game. The winner of the game is a player with the king on the board.
5. Edit the executable class Main.
    - Create two players with **opposite colors**.
    - Create an instance of the game.
    - Set the initial layout of the game.
    - Make a move from `a2` to `a3`
    - Print the second row of the board as 8 separate lines. Each line represents one tile from the row and should 
      either be a piece (e.g. `K`, `P`...) or `null` if the tile does not contain any piece.


Hints:
- Do not forget to write `@Override` when you implement a method from the interface.
- [The initial position of chess pieces.](https://en.wikipedia.org/wiki/Rules_of_chess#Initial_setup)
- You will need `new Scanner(System.in);`, in `Game`.
