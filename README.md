## First Iteration

1. Create a package `Demo` and move class `Main` to it.
2. Create a `Player` class without a constructor and with attribute `name` of type `String`.
   Add getter and setter to the class.
3. Create a `Coordinates` class with attribute `letterNumber` and `number` of type `int`.
   Add getters, setters and constructor with all attributes to the class.
   Add methods `averageOfCoordinates` and `add`.
    - Method `averageOfCoordinates` returns average of `letterNumber` and `number` of coordinates.
    - Method `add` takes another coordinates as the input parameter and
      returns new coordinates that count as one coordinate plus the other coordinate.
      It is calculated by components (as well as complex numbers).
4. Create a `Piece` class with attribute `id` of type `long`.
    - Add constructor and getter. We will not need setter.
5. Edit the executable class `Main`
    - Remove the print of Hello world!.
    - Create a new `Player`. Set name `"Matko"` and print name using setter and getter.
    - Create two coordinates. The First has coordinates `[1, 7]` and the second has `[5, 8]`.
    - Print the average of the first coordinates.
    - Compute first plus second coordinates and print the result on separate lines.

Hints:
- do not forget the documentation
    - every class must have `@author <YourName>` for example `@author Jan Hrasko`
    - every public method must have documentation (except getters and setters)
        - if the method has a return value then must have `@returns` in the documentation
        - if the method has input parameters then must have `@param <nameOfParameter>` in the documentation
    - if you type `\**` above the method and press enter, you will automatically generate tags
      that you must fill into a documentation of the method
- if you want to write `System.out.println` you can write `sout` and press enter
