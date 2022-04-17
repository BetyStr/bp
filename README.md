## First Iteration

1. Create a package `Demo` and move class `Main` to it.
2. Create a `Player` class with attribute `name` of type `String`. Add the getter and setter to the class.
3. Create a `Coordinates` class with attribute `letterNumber` and `number` of type `int`.
   Add getters, setters, and constructor with all attributes to the class.
   Add methods `averageOfCoordinates` and `add`.
   - `averageOfCoordinates` return average of two coordinates.
   - `add` returns new coordinates that count as one coordinate plus the other coordinate (which we get as a parameter).
     It is calculated by components (as well as complex numbers).
4. Create a `Piece` class with attribute `id` of type `long`.
   - In constructor without parameters set unique `id` using `AtomicLong`
5. Edit the executable class `Main`
   - Create a new `Player`. Set name `"Matko"` and print name using setter and getter.
   - Create two coordinates. The First has coordinates `(1, 7)` and the second has `(5, 8)`.
   - Print the average of the first coordinates.
   - Compute first plus second coordinates and print the result on separate lines.

Hints:
- in `Piece` class create a constant of instance `AtomicLong`
- class `AtomicLong` have method `getAndIncrement`
- do not forget the documentation
  - every public method must have documentation (except getters and setters)
    - if the method has a return value then must have `@returns` in the documentation
    - if the method has input parameters then must have `@param <nameOfParameter>` in the documentation
- if you want to write `System.out.println` you can write `sout` and tap Enter
