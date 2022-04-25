## Fourth iteration

1. Make class `Piece` implements interface `Prototype`. Implement method `makeClone`, 
which return new `Piece` with same attributes except attribute `id`. `Id` will be unique. 
- Override `toString` methods to return first letter of `pieceType`
2. Add to class `Board` methods `getAllPiecesFromBoard`, `getColor` and `getPiece`.
- Method `getAllPiecesFromBoard` returns list of all pieces on board.
- Method `getColor` takes two integers as input parameter and return color of piece on board at position of these integers. 
If at position is not piece or position is out of board than returns null.
- Method `getPiece` is overload method, which takes one input parameter of type `Coordinates`.
3. Create interface `Playable`. This interface serves as a template so that every game has certain methods 
   without which it would not be playable.
- Interface will have method:
  - Method `setInitialSet` sets the initial chess layout of the pieces on the board. 
  - Method `move` takes two coordinates as input parameter. The method moves the piece from the first position 
  to second position on board. You can assume that the figure is in the first position. 
  Do not forget promotion. If the pawn walks to the last square of the board, he turns into a queen.
  - Method `play` without parameters. Method demonstrating game. 
  Take parameters from user and make moves until game is over.
4. Modify class `Game`.
- Make class `Game` to implements interface `Playeble`.
- Implement methods from interface `Playeble`.
- Add method `updateStatus`, which control if on board is two kings. If one king is missing change status of game. 
  Winner of game is player with King on board.
5. Edit the executable class Main.
- Create two players with opposite colors.
- Create instance if game. Set initial set to game.
- Make move from `(1,1)` to `(1,2)`
- Print all pieces from row, which was move on separate lines.


Hints:
- do not forget `@Override` 
- [Initial position of pieces](https://en.wikipedia.org/wiki/Rules_of_chess#Initial_setup)