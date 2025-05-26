package mahmh.customdsa.arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    void testAlreadySorted() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0, 4.0, 5.0}, array);
    }

    @Test
    void testReverseOrder() {
        double[] array = {5.0, 4.0, 3.0, 2.0, 1.0};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0, 4.0, 5.0}, array);
    }

    @Test
    void testWithDuplicates() {
        double[] array = {2.2, 1.1, 3.3, 2.2, 1.1};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{1.1, 1.1, 2.2, 2.2, 3.3}, array);
    }

    @Test
    void testSingleElement() {
        double[] array = {42.0};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{42.0}, array);
    }

    @Test
    void testEmptyArray() {
        double[] array = {};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{}, array);
    }

    @Test
    void testNegativeValues() {
        double[] array = {-2.5, -1.1, 0.0, 3.4, -3.3};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{-3.3, -2.5, -1.1, 0.0, 3.4}, array);
    }

    @Test
    void testRandomUnsorted() {
        double[] array = {2, 8.2, 5.2, 5.1, 3, 9.6, 4, 1, 7};
        MergeSort.sort(array);
        assertArrayEquals(new double[]{1, 2, 3, 4, 5.1, 5.2, 7, 8.2, 9.6}, array);
    }
}