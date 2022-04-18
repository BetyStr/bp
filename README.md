## Second iteration 

1. Change class `Coordinates` to record and delete unnecessary code.
2. Override `toString` in `Coordinates`. Method `toString` returns the string `(<letterNumber>, <number>)`.
3. Create package `utils` and class `BoardNotation` in it.
- private constructor to avoid initializing
- private constant for char value of letter `A`
- with static method:
  - `getNotationOfCoordinates(int x, int y)`
  - `getCoordinatesOfNotation(char x, int y)`
    - these methods transform coordinates of board to chess notation and reverse
    - `0-7 <=> a-h`
    - `0-7 <=> 1-8`
4. Create `Board` class 
- with attributes
  - `Pieces` which is two-dimensional array of `Piece`
  - `round` of type int with getter and setter
  - public constant `SIZE`
- with methods:
  - `getPiece(int letterNumber, int number)` 
  - `boolean inRange(Coordinates coordinates)`
  - `boolean isEmpty(int x, int y)`
  - `void putPieceOnBoard(int letterNumber, int number, Piece piece)`
  - `findCoordinatesOfPieceById(long id)` (todo maybe MOVE to 03)


Hints:
- do not forget change in your code `getLetterNumber()` and `getNumber()` to `number()` and `letterNumber()`
- value for char `A` is `97`