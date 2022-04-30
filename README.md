## Sixth iteration

- Add `equals` and `hashcode`:
  - `Board`
  - `Piece`
  - `Game`
- change `toString` in `Piece`
- Memento 
  - add attribute `LinkedList<Board> history`
  - `Playeble` implement `Caretaker`
  - `Board` implement `Originator<Board>`
    - construktory
- create package `moves`
- base on interface `Move` create:
  - `Diagonal`
  - `Jump`
  - `Straight`
  - Knight