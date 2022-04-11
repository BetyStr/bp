package cz.muni.fi.pb162.project.testing;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Collections;
import java.util.Objects;

/**
 * Basic class designed to practice writing basic tests.
 *
 * @author Alzbeta Strompova
 */
public class MyArray {

    private final List<Integer> arrayList;

    /**
     * Constructor without parameters
     */
    public MyArray() {
        arrayList = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param arrayList is array of Integers
     */
    public MyArray(Integer[] arrayList) {
        this.arrayList = new ArrayList<>(Arrays.asList(arrayList));
    }

    public List<Integer> getArrayList() {
        return Collections.unmodifiableList(arrayList);
    }

    /**
     * Method return average of integers.
     *
     * @return average of integers.
     */
    public double calculateAverage() {
        var average = arrayList
                .stream()
                .mapToDouble(a -> a)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    /**
     * Method return min of integers.
     *
     * @return min of integers.
     */
    public int getMin() {
        return Collections.min(arrayList);
    }

    /**
     * Method return max of integers.
     *
     * @return max of integers.
     */
    public int getMax() {
        return Collections.max(arrayList);
    }

    /**
     * Method return List of integers that entered by adding {@code number}
     *
     * @param number to add to every item of List
     * @return new List of integers that entered by adding {@code number}
     */
    public List<Integer> addNumber(int number) {
        return arrayList
                .stream()
                .map(x -> x + number)
                .toList();
    }

    @Override
    public String toString() {
        return "MyArray is: " + arrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyArray myArray = (MyArray) o;
        return Objects.equals(arrayList, myArray.arrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrayList);
    }
}
