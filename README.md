## Second iteration 

Tasks:
- change `Coordinates` to record and override `toString`
- create package utils and class `BoardNotation` in it, with static method:
  - private constants for value of char "A"
  - `getNotationOfCoordinates`
  - `getCoordinatesOfNotation`
- create `Board` with attributes:
  - `Pieces` which is two-dimensional array of `Piece`
  - `int round`
  - public constant `SIZE`
  - methods:
    - `getPiece(int letterNumber, int number)`
    - `getPiece(Coordinates position)`
    - `boolean inRange(Coordinates coordinates)`
    - `boolean isEmpty(int x, int y)`
    - `void putPieceOnBoard(int letterNumber, int number, Piece piece)`
