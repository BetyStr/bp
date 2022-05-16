## Second iteration
In this iteration, you will practice the work with immutable objects, constants, arrays, and character 
conversion to numbers and vice versa.


1. Create a package `utils` and a **utility class** `BoardNotation` in it.
    - Add a suitable constructor to ensure that the class cannot be instantiated.
    - Add a **constant** for the char value of the letter `a`. We do not need any other numerical values for letters 
      because we can compute them from the value of `a`.
        - Create methods `getNotationOfCoordinates` and `getCoordinatesOfNotation`.
          These methods transform the coordinates of the board to the chess notation and vice versa.
          > `getNotationOfCoordinates(0, 5)` takes two integers and returns string `"a6"` <p>
          `getCoordinatesOfNotation(b, 3)` takes character, integer and returns coordinates with attributes `2` and `4`.
2. Modify the class `Coordinates` to **record**.
    - Do not forget to delete unnecessary code.
    - **Override** `toString` in `Coordinates`. The method `toString` returns the string in following format: `<letterNumber><number>`.
      >`toString()` is a standard method that exists in every class by default(we will learn later how it is arranged).
      Therefore, you need to **add the annotation @Override** above the method header.
3. Update the class `Piece`.
    - **Remove** the constructor with the parameter.
    - Add a new constructor without parameters. The constructor sets the **unique** `id` using `AtomicLong`. 
      The piece should have a unique id so that when we add the type and color, we would know that 
      two pieces with the same color and the same type do not necessarily have to be the same 
      (e.g., in chess, each player starts with eight pawns).
4. Create the `Board` class.
    - A board consists of **a two-dimensional array** of pieces. 
      Moreover, it remembers the number of played rounds of the game in a numeric attribute `round`.
    - Add a getter and setter for the attribute `round`.
    - Add a public **constant** representing the size of the square board with the default value of `8`.
    - Add methods that will be used to find suitable moves of the pieces on the board.
        - Method `getPiece` takes two integers as input parameters (representing coordinates).
          The method returns the piece at the position of the coordinates.
          If there is no piece on the given coordinates or the position is not on the board, 
          the method returns `null`.
        - Method `inRange` takes one coordinate as an input parameter
          and returns `false` if the coordinate is out of bounds of the board. Otherwise, it returns `true`.
          Note that the method does not use any class attributes.
        - Method `isEmpty`, which takes two integers as input parameters (representing coordinates)
          and returns `true` if there is no piece at the given position. Otherwise, it returns `false`.
        - Method `putPieceOnBoard` takes two integers (representing coordinates) and a piece as an input parameter
          and adds the piece to the board at the position of the input integers.
          Method `findCoordinatesOfPieceById` takes the id of type `long` as an input parameter
          and returns the piece with the same `id` from the board. If it does not exist, then it returns `null`.
5. Edit the executable class `Main`.
    - Remove the creation of the player.
    - Add a print statement of `getNotationOfCoordinates` from `BoardNotation` with input parameters `1` and `3`
       at the beginning.

Hints:
- The notation for our board is the following:

  <img src="images/chessboardnotation.png" alt="chessboard" width="300"/>.
- In the `Piece` class, create a private constant of instance `AtomicLong` 
  (`import java.util.concurrent.atomic.AtomicLong;`).
- Class `AtomicLong` has the method `getAndIncrement`.
- Do not forget to change `getLetterNumber()` and `getNumber()` to `number()` and `letterNumber()` in your code.
- The value for the char `a` is `97`.
