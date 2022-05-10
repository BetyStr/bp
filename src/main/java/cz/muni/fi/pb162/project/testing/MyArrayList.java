package cz.muni.fi.pb162.project.testing;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Collections;
import java.util.Objects;

/**
 * Basic class designed to practice writing simple tests.
 *
 * @author Alzbeta Strompova
 */
public class MyArrayList {

    private final List<Integer> arrayList;

    /**
     * Constructor without parameters.
     */
    public MyArrayList() {
        arrayList = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param arrayList is array of Integers
     */
    public MyArrayList(Integer[] arrayList) {
        this.arrayList = Arrays.asList(arrayList);
    }

    public List<Integer> getArrayList() {
        return Collections.unmodifiableList(arrayList);
    }

    /**
     * Method return average of integers.
     *
     * @return average of integers or 0 if the list is empty.
     */
    public double calculateAverage() {
        var average = arrayList
                .stream()
                .mapToDouble(a -> a)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    /**
     * Method return min of integers or {@code NoSuchElementException} if the list is empty.
     *
     * @return minimum of integers.
     */
    public int getMin() {
        return Collections.min(arrayList);
    }

    /**
     * Method return max of integers or {@code NoSuchElementException} if the list is empty.
     *
     * @return maximum of integers.
     */
    public int getMax() {
        return Collections.max(arrayList);
    }

    /**
     * Method adds the number to every element of the list and return it.
     *
     * @param number to add to every item of the list.
     * @return new list of integers created by adding {@code number}.
     */
    public List<Integer> addNumber(int number) {
        return arrayList
                .stream()
                .map(x -> x + number)
                .toList();
    }

    /**
     * Method returns the list of integers which consist of element of {@code arrayList} which met the condition.
     * Condition: an item of list > {@code number}.
     *
     * @param number with which we compare.
     * @return new list of integers created by control the condition.
     */
    public List<Integer> biggerThenNumber(int number) {
        return arrayList
                .stream()
                .filter(x -> x > number)
                .toList();
    }

    @Override
    public String toString() {
        return "MyArrayList is: " + arrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyArrayList other = (MyArrayList) o;
        return Objects.equals(arrayList, other.arrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrayList);
    }

}
