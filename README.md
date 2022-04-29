## Fifth iteration

1. Add `DRAUGHTS_KING` and  `DRAUGHTS_MAN` to `PieceType`. 
2. Create classes `Chess` that will inherit from the class `Game`.
   - Make `Game` abstract with abstract method `updateStatus`.
   - Move all specific code about chess to `Chess`.
3. Create class `Draughts`. It will have different initial set, move and conditions of ending from `Chess`.
   - It will inherit from class `Game`.
     - Method `setInitialSet` sets initial layout of draughts pieces.
     - Method `move` will work with two options.
       - If you move piece only by 1 square that method put piece to new position. 
       - If you move piece by 2 square that method remove piece between old position and new position. 
         > For example if you move piece from `(0,0)` to `(2,2)` than you must remove piece on `(1,1)`.
       - You Can assume that you get valid input.
   - Method `updateStatus` changes status if a player wins. If there is no piece of one color on the board, 
    the player of the other color has won.
4. Add control of castling in method `move` in `Chess`.
   - When castling occurs then input of method `move` will be old position and new position of king. 
     You must in this method move rook. (It is not necessary to check whether castling may occur. 
     Assume that all conditions are fulfilled.)
   
   <img src="images/castling.jpg" alt="castling" width="600"/>.

5. Add `toString` to `Board`. Return value `toString()` of board with initial position of pieces looks like this
```
   1   2   3   4   5   6   7   8
  --------------------------------
A | R | P |   |   |   |   | P | R |
  --------------------------------
B | K | P |   |   |   |   | P | K |
  --------------------------------
C | B | P |   |   |   |   | P | B |
  --------------------------------
D | Q | P |   |   |   |   | P | Q |
  --------------------------------
E | K | P |   |   |   |   | P | K |
  --------------------------------
F | B | P |   |   |   |   | P | B |
  --------------------------------
G | K | P |   |   |   |   | P | K |
  --------------------------------
H | R | P |   |   |   |   | P | R |
  --------------------------------
```

Hints:
- Use `StringBuilder` instead of adding strings using the plus sign.
- Use `System.lineSeparator()` instead of `\n`.
- [Initial position of Draughts.](https://en.wikipedia.org/wiki/English_draughts#Starting_position)
 
