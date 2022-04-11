## Sixth iteration

Tasks:
- in `TypeOfPiece` add `getSymbol` (trying to force them to use `hashMap`)
- add `equals` and `hashcode`:
  - `Board`
  - `Piece`
  - `Game`
- change `toString` in `Piece`
- Memento 
  - add attribute `LinkedList<Board> history`
  - `Game` implement `Caretaker`
  - `Board` implement `Originator<Board>`
- create package `moves`
- base on interface `Move` create:
  - `Diagonal`
  - `Jump`
  - `Straight`