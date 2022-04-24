## Second iteration 

1. Create package `utils` and class `BoardNotation` in it.
- Add private constructor to avoid initializing.
- Add private constant for char value of letter `A`.
- Create static method `getNotationOfCoordinates` and `getCoordinatesOfNotation`:
  - these methods transform coordinates of board to chess notation and reverse
  - `getNotationOfCoordinates(int x, int y)`   
  > for example `getNotationOfCoordinates(0, 5)` returns string `a6`
- `getCoordinatesOfNotation(char x, int y)`
  > for example `getCoordinatesOfNotation(b, 3)` returns string `24`
2. Change class `Coordinates` to record.
- Do not forget to delete unnecessary code.
- Override `toString` in `Coordinates`. Method `toString` returns the string `<letterNumber><number>`.
  >`toString()` is the standard method that exists in every class (we'll learn later how it is arranged).
  > Therefore, add the annotation @Override above the method header.
3. In class `Piece`  
- remove constructor with parameter.
- add constructor without parameters, which set unique id using `AtomicLong`.
4. Create `Board` class 
- with attributes
  - `squares` which is two-dimensional array of `Piece`.
  - `round` of type int with getter and setter.
  - public constant `SIZE` with value `8`.
- with methods:
  - `getPiece`, which takes two integers as input parameter(represent coordinates) 
  and returns piece at position of coordinates. 
  If there is no piece on coordinates or position are out of `board` than returns `null`.
  - `inRange`, which takes one coordinates as input parameter
    and returns `true` if coordinates is not out of array `squares`. Otherwise, returns `false`.
    Note that the method does not use any class attribute.
  - `isEmpty`, which takes two integers as input parameter(represent coordinates)
    and returns `true` if ot position of coordinates is not piece. Otherwise, returns `false`.
  - `putPieceOnBoard`, which takes two integers and piece as input parameter
     and add piece to `squares` at position of input integers.
  - `findCoordinatesOfPieceById`, which takes id of type `long` as input parameter 
  and return piece with same `id` from `squares`. If it does not exist then return `null`.
5. Edit the executable class `Main`:
- remove creating player
- add at begging print `getNotationOfCoordinates` from `BoardNotation` with input parameters `1` and `3`

Hints:
- in Piece class create a private constant of instance `AtomicLong` (`import java.util.concurrent.atomic.AtomicLong;`)
- class `AtomicLong` have method `getAndIncrement`
- do not forget change in your code `getLetterNumber()` and `getNumber()` to `number()` and `letterNumber()`
- value for char `A` is `97`