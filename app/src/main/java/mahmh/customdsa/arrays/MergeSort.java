package mahmh.customdsa.arrays;

public class MergeSort {
    /** Sorts the array by dividing it into subarrays, then sorting and merging the subarrays to form the final sorted array. */
    public static void sort(double[] array) {
        final int length = array.length;
        if (length <= 1) return;

        final int middle = length/2;
        double[] leftArray = new double[middle];
        double[] rightArray = new double[length - middle];

        int i=0, j=0;
        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
        }

        sort(leftArray);
        sort(rightArray);
        merge(leftArray, rightArray, array);
    }

    /** Runnable example. */
    public static void main(String[] args) {
        double[] array = {2, 8.2, 5.2, 5.1, 3, 9.6, 4, 1, 7};
        MergeSort.sort(array);
        printArray(array);
    }

    /** Merges the left and right subarrays according to Merge Sort conditions. */
    private static void merge(double[] leftArray, double[] rightArray, double[] array) {
        final int leftLength = array.length / 2;
        final int rightLength = array.length - leftLength;
        int i=0, l=0, r=0;

        // Check the conditions for merging
        while (l < leftLength && r < rightLength) {
            if (leftArray[l] < rightArray[r]) {
                 array[i] = leftArray[l];
                 l++;
            } else {
                array[i] = rightArray[r];
                r++;
            }

            i++;
        }

        // There could be one element that we cannot compare with another
        while (l < leftLength) {
            array[i] = leftArray[l];
            i++;
            l++;
        }

        while (r < rightLength) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

    /** Prints the array to stdout for debugging. */
    private static void printArray(double[] array) {
        System.out.print("[");
        for (int i=0; i < array.length; i++) {
            System.out.print(array[i] + (i+1 == array.length ? "" : ", "));
        }
        System.out.print("]");
    }
}