## Second iteration

1. Create package `utils` and class `BoardNotation` in it.
    - Add a private constructor without parameters to avoid initializing.
    - Add private constant for char value of letter `a`.
        - Create static method `getNotationOfCoordinates` and `getCoordinatesOfNotation`.
          These methods transform coordinate of board to chess notation and reverse.
          > `getNotationOfCoordinates(0, 5)` takes two integers and returns string `"a6"` <p>
          `getCoordinatesOfNotation(b, 3)` takes character, integer and returns string `"24"`
2. Change class `Coordinates` to record.
    - Do not forget to delete unnecessary code.
    - Override `toString` in `Coordinates`. Method `toString` returns the string of format `<letterNumber><number>`.
      >`toString()` is the standard method that exists in every class (we'll learn later how it is arranged).
      Therefore, add the annotation @Override above the method header.
3. Update class `Piece`.
    - Remove constructor with parameter.
    - Add constructor without parameters, which set unique id using `AtomicLong`.
4. Create the `Board` class.
    - Her attributes will be a two-dimensional array of `Piece` and `round` of type `int`.
    - Add getter and setter to attribute `round`.
    - Add public constant `SIZE` with value `8`.
    - Add these methods.
        - Method `getPiece`, which takes two integers as input parameters (represent coordinate)
          and returns the piece at the position of coordinate.
          If there is no piece on coordinate or position that is out of board then returns `null`.
        - Method`inRange`, which takes one coordinate as an input parameter
          and returns `false` if the coordinate is out of the board. Otherwise, returns `true`.
          Note that the method does not use any class attribute.
        - Method `isEmpty`, which takes two integers as input parameters (represent coordinate)
          and returns `true` if the position of coordinate is not any piece. Otherwise, returns `false`.
        - Method `putPieceOnBoard`, which takes two integers(represent coordinate) and the piece as input parameter
          and adds a piece to the board at a position of input integers.
        - Method `findCoordinatesOfPieceById`, which takes the id of type `long` as an input parameter
          and returns the piece with the same `id` from the board. If it does not exist then return `null`.
5. Edit the executable class `Main`.
    - Remove creating player.
    - Add at begging print `getNotationOfCoordinates` from `BoardNotation` with input parameters `1` and `3`.

Hints:
- Notation for our board will be

  <img src="images/chessboardnotation.png" alt="chessboard" width="300"/>.
- In Piece class create a private constant of instance `AtomicLong` (`import java.util.concurrent.atomic.AtomicLong;`).
- Class `AtomicLong` have method `getAndIncrement`.
- Do not forget change in your code `getLetterNumber()` and `getNumber()` to `number()` and `letterNumber()`.
- Value for char `a` is `97`.
