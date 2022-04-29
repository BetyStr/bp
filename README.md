## First iteration

1. Create a package `Demo` and move class `Main` to it.
2. Create a `Player` class with attribute `name` of type `String`. Add getter and setter to the class. 
   Do not create constructor. We will use default one in this iteration.
3. Create a `Coordinate` class with attribute `letterNumber` and `number` of type `int`.
   - Add getters, setters and constructor with all attributes to the class.
   - Add methods `averageOfCoordinate` and `add`.
     - Method `averageOfCoordinate` returns average of `letterNumber` and `number` of coordinate.
     - Method `add` takes another coordinate as the input parameter and
       returns new coordinate that count as one coordinate plus the other coordinate.
       It is calculated by components (as well as complex numbers).
4. Create a `Piece` class with attribute `id` of type `long`.
    - Add constructor and getter. We will not need setter.
5. Edit the executable class `Main`.
    - Remove the print of `Hello world!`.
    - Create a new `Player`. Set name "Matko" and print name using setter and getter.
    - Create two coordinates. The First has will be `(1, 7)` and the second will be `(5, 8)`.
    - Print the average of the first coordinate.
    - Compute first plus second coordinate and print the result on separate lines.
6. Every submitted iteration must pass tests and checkstyle.
- Test your code using unit tests in the package `src/test/java`.
- To run all tests and checkstyle run `mvn clean test` on the command line.

Hints:
 - Do not forget the documentation.
   - Every class must have `@author <YourName>` for example `@author Jan Hrasko`.
   - Every public method must have documentation (except getters, setters and override methods).
     - If the method has an input parameters then must have `@param <nameOfParameter>` in the documentation.
     - If the method has a return value then must have `@returns` in the documentation.
   - If you type `\**` above the method and press enter, you will automatically generate tags that 
     you must fill into a documentation of the method.
 - If you want to write `System.out.println` you can write `sout` and press enter.