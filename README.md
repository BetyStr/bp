## First iteration

1. Create a package `Demo` and move class `Main` into it.
2. Create a `Player` class with an attribute `name` of type `String`. Add getter and setter to the class.
   Do not create a constructor. We will use the default one in this iteration.
3. Create a `Coordinate` class with attributes `letterNumber` and `number` of type `int`.
    - Add getters, setters, and constructor with all attributes to the class.
    - Add methods `averageOfCoordinate` and `add`.
        - The method `averageOfCoordinate` returns average of `letterNumber` and `number` of coordinate.
        - The method `add` takes another coordinate as the input parameter and
          returns a new coordinate that counts as one coordinate plus the other coordinate.
          It is calculated by components (as well as complex numbers).
4. Create a `Piece` class with an attribute `id` of type `long`.
    - Add constructor and getter. We will not need a setter.
5. Edit the executable class `Main`.
    - Remove the print of `Hello world!`.
    - Create a new `Player`. Set name "Matko" and print name using setter and getter.
    - Create two coordinates. The first will be `(1, 7)` and the second will be `(5, 8)`.
    - Print the average of the first coordinate.
    - Compute the first plus second coordinate and print the result on the separate lines.
6. Every submitted iteration must pass tests and check style.
- Test your code using unit tests in the package `src/test/java`.
- To run all tests and check style run `mvn clean test` on the command line.

Hints:
- Do not forget to write the documentation.
    - Every class must have `@author <YourName>` for example `@author Jan Hrasko`.
    - Every public method must have documentation (except getters, setters, and override methods).
        - If the method has an input parameter then there must be `@param <nameOfParameter>` in the documentation.
        - If the method has a return value then there must be `@returns` in the documentation.
    - If you type `\**` above the method and press enter, you will automatically generate tags that
      you must fill into a documentation of the method.
- If you want to write `System.out.println` you can write `sout` and press enter.
