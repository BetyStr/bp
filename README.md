## First iteration
The first iteration aims to get acquainted with objects, encapsulation, and work with primitive types.

1. Create a package `demo` and move class `Main` into it.
2. Create a `Player` class with an attribute `name` of type `String`. Add getter and setter to the class.
   Do not create a constructor. We will use the default one in this iteration.
3. Create a `Coordinates` class with attributes `letterNumber` and `number` of type `int`. 
   The class represents coordinates on the board. The classic chessboard uses coordinates from 1 to 8 and A to H. 
   We work with the field in other iterations, so for simplicity, we will store the coordinates as numeric types 
   because the characters can be easily converted to numbers(see [ASCII](https://en.wikipedia.org/wiki/ASCII)).
    - Add getters, setters, and constructor with all the attributes.
    - Add methods `averageOfCoordinates` and `add`.
        - The method `averageOfCoordinates` returns the average of `letterNumber` and `number` of coordinates.
        - The method `add` takes other coordinates as an input parameter and calculates the new coordinates
          by summing the two. ou can calculate the sum by summing the individual components (just as complex numbers).
          It does not change the **state** of the original object.
          It enables chain calls, e.g.,
       ```
             Coordinates c = (new Coordinates(1, 2))
                                    .add(new Coordinate(3, 5))
                                    .add(new Coordinate(7, 0));
      ```
5. Create a `Piece` class with an attribute `id` of type `long`.
    - Add a constructor and a getter. We will not need a setter.
6. Edit the executable class `Main`.
    - Remove the print statement for `Hello world!`.
    - Create a new `Player`. Set name "Mat" and print the name using setter and getter.
    - Create two coordinates. The first will be `(1, 7)`, and the second will be `(5, 8)`.
    - Print the average of the first coordinates.
    - Compute the first plus second coordinates and print the result on the separate lines.
7. Every submitted iteration must pass tests and check style.
   - Test your code using unit tests in the package `src/test/java`.
   - To run all tests and check style run `mvn clean test` from the command line.

Hints:
- Do not forget to write the documentation.
    - Every class must have `@author <YourName>` for example `@author Jan Hrasko`.
    - Every public method must have its documentation (except getters, setters, and override methods).
        - If the method has an input parameter, there must be `@param <nameOfParameter>` in the documentation.
        - If the method has a return value, there must be `@returns` in the documentation.
    - If you type `/**` above the method and press enter, you will automatically generate tags that
      you must fill into a documentation of the method.
