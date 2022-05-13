## Second iteration
This iteration serves to practise work with immutable objects, constants, arrays and character conversion to numbers and vice versa.


1. Create a package `utils` and a **utility class** `BoardNotation` in it.
    - Add a suitable constructor to ensure that the class cannot be instantiated.
    - Add **constant** for char value of letter `a`. We do not need other numerical values of letters we do not need because we can compute them from the value of `a`.
        - Create method `getNotationOfCoordinates` and `getCoordinatesOfNotation`.
          These methods transform coordinates of board to chess notation and vice versa.
          > `getNotationOfCoordinates(0, 5)` takes two integers and returns string `"a6"` <p>
          `getCoordinatesOfNotation(b, 3)` takes character, integer and returns coordinates with attributes `2` and `4`.
2. Change class `Coordinates` to record.
    - Do not forget to delete unnecessary code.
    - **Override** `toString` in `Coordinates`. Method `toString` returns the string of format `<letterNumber><number>`.
      >`toString()` is the standard method that exists in every class (we will learn later how it is arranged).
      Therefore, **add the annotation @Override** above the method header.
3. Update class `Piece`.
    - Remove the constructor with the parameter.
    - Add constructor without parameters, which set **unique** id using `AtomicLong`.
4. Create the `Board` class.
    - A board consists of a two-dimensional array of pieces. Moreover, it remembers the number of played rounds of the game in a numeric attribute `round`.
    - Add getter and setter to attribute `round`.
    - Add public **constant** represents the size of the square board with default value `8`.
    - Add methods that will be used to find suitable moves of the pieces on the checkerboard.
        - Method `getPiece` takes two integers as input parameters (represent coordinates).
          Furthermore, it returns the piece at the position of coordinate.
          If there is no piece on coordinate or position out of the board, then the method returns `null`.
        - Method`inRange` takes one coordinate as an input parameter
          and returns `false` if the coordinate is out of the board. Otherwise, it returns `true`.
          Note that the method does not use any class attribute.
        - Method `isEmpty`, which takes two integers as input parameters (represent coordinates)
          Furthermore, it returns `true` if there is no piece in the given position. Otherwise, it returns `false`.
        - Method `putPieceOnBoard` takes two integers (represent coordinates) and a piece as an input parameter
          and adds the piece to the board at the position of the input integers.
          Method `findCoordinatesOfPieceById` takes the id of type `long` as an input parameter
          and returns the piece with the same `id` from the board. If it does not exist, then it returns `null`.
5. Edit the executable class `Main`.
    - Remove creating player.
    - Add at begging print `getNotationOfCoordinates` from `BoardNotation` with input parameters `1` and `3`.

Hints:
- Notation for our board will be

  <img src="images/chessboardnotation.png" alt="chessboard" width="300"/>.
- In Piece class create a private constant of instance `AtomicLong` (`import java.util.concurrent.atomic.AtomicLong;`).
- Class `AtomicLong` has method `getAndIncrement`.
- Do not forget to change in your code `getLetterNumber()` and `getNumber()` to `number()` and `letterNumber()`.
- Value for char `a` is `97`.
