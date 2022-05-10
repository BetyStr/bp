package cz.muni.fi.pb162.project.testing;

import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Alzbeta Strompova
 */
class MyArrayListTest {

    private final MyArrayList myArrayList = new MyArrayList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    private final MyArrayList myArrayList2 = new MyArrayList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    private final MyArrayList myArrayList3 = new MyArrayList(new Integer[]{42, 2, 7, 7, 15, -71});


    @Test
    void getArrayList() {
        assertNewOrUnmodifiableCollection(myArrayList, myArrayList.getArrayList());
        assertNewOrUnmodifiableCollection(myArrayList3, myArrayList3.getArrayList());
    }

    private void assertNewOrUnmodifiableCollection(MyArrayList myArrayList, Collection<?> collection) {
        int expectedSize = myArrayList.getArrayList().size();
        try {
            collection.clear();
            assertThat(myArrayList.getArrayList())
                    .as("Method returns modifiable collection - return new or unmodifiable.")
                    .hasSize(expectedSize);
        } catch (UnsupportedOperationException e) {
            // ok (unmodifiable)
        }
    }

    @Test
    void calculateAverage() {
        Assertions.assertEquals(5, myArrayList.calculateAverage());
        Assertions.assertEquals(5, myArrayList2.calculateAverage());
        org.assertj.core.api.Assertions.assertThat(myArrayList3.calculateAverage()).isBetween(0.333332, 0.33334);
    }

    @Test
    void getMin() {
        Assertions.assertEquals(1, myArrayList.getMin());
        Assertions.assertEquals(1, myArrayList2.getMin());
        Assertions.assertEquals(-71, myArrayList3.getMin());

    }

    @Test
    void getMax() {
        Assertions.assertEquals(9, myArrayList.getMax());
        Assertions.assertEquals(9, myArrayList2.getMax());
        Assertions.assertEquals(42, myArrayList3.getMax());
    }

    @Test
    void addNumber() {
        org.assertj.core.api.Assertions.assertThat(myArrayList.addNumber(0))
                .isEqualTo(myArrayList.getArrayList());
        org.assertj.core.api.Assertions.assertThat(myArrayList2.addNumber(-2))
                .containsOnly(-1, 0, 1, 2, 3, 4, 5, 6, 7);
        org.assertj.core.api.Assertions.assertThat(myArrayList3.addNumber(3))
                .containsOnly(45, 5, 10, 10, 18, -68);
    }

    @Test
    void biggerThenNumber() {
        org.assertj.core.api.Assertions.assertThat(myArrayList.biggerThenNumber(10)).isEmpty();
        org.assertj.core.api.Assertions.assertThat(myArrayList2.biggerThenNumber(-2))
                .isEqualTo(myArrayList2.getArrayList());
        org.assertj.core.api.Assertions.assertThat(myArrayList3.biggerThenNumber(4))
                .containsOnly(42, 7, 7, 15);
    }

    @Test
    void testToString() {
        Assertions.assertEquals("MyArrayList is: [1, 2, 3, 4, 5, 6, 7, 8, 9]", myArrayList.toString());
        Assertions.assertEquals("MyArrayList is: [1, 2, 3, 4, 5, 6, 7, 8, 9]", myArrayList2.toString());
        Assertions.assertEquals("MyArrayList is: [42, 2, 7, 7, 15, -71]", myArrayList3.toString());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(myArrayList, myArrayList2);
        Assertions.assertNotEquals(myArrayList, myArrayList3);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(myArrayList.hashCode(), myArrayList2.hashCode());
        Assertions.assertNotEquals(myArrayList.hashCode(), myArrayList3.hashCode());
    }

}
