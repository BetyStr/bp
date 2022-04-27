## Fifth iteration

1. Add `DRAUGHTS_KING` and  `DRAUGHTS_MAN` to `PieceType`. 
2. Create classes `Chess` and `Draughts` that inherit from the class `Game`.
- Draughts will have different initial set, move and conditions of ending.
  - Move will remove piece between old position and new position.  
- Move all specific code about chess to `Chees`. 
- Make `Game` abstract with abstract method `updateStatus`.
3. Add control of castling in `move` in `Chess`.
- When castling occurs then input of method `Move` will be old position and new position of King. 
  You must in this method move Rook. (It is not necessary to check whether castling may occur. 
  Assume that all conditions are fulfilled.) 
4. Add `toString` to `Board`.
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
