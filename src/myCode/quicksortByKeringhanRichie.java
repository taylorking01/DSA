package myCode;
import java.util.Arrays;

public class quicksortByKeringhanRichie {

    // Main quicksort function that sorts the array v[] between indices left and right
    public static void quicksort(int[] v, int left, int right) {
        if (left >= right) { // Base case: No sorting needed for fewer than 2 elements
            return;
        }

        // Pivot selection: Move the middle element to the front (v[left])
        swap(v, left, (left + right) / 2);

        int last = left; // This will track the position of the partition boundary

        // Partitioning: Arrange elements smaller than the pivot to the left
        for (int i = left + 1; i <= right; i++) {
            if (v[i] < v[left]) {
                swap(v, ++last, i); // Swap smaller elements to the left of the pivot
            }
        }

        // Move the pivot element to its final sorted position
        swap(v, left, last);

        // Recursively sort the two partitions
        quicksort(v, left, last - 1); // Sort the left partition
        quicksort(v, last + 1, right); // Sort the right partition
    }

    // Helper function to swap two elements in the array
    public static void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    // Main function to run tests and output results
    public static void main(String[] args) {
        // Test the quicksort implementation using assert statements
        testQuicksort();

        // Sample array to demonstrate sorting
        int[] array = {9, 2, 7, 3, 5, 6, 1, 4, 8};
        System.out.println("Original array: " + Arrays.toString(array));

        // Call quicksort to sort the array
        quicksort(array, 0, array.length - 1);

        // Output the sorted array
        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    // Test function with assert statements
    public static void testQuicksort() {
        int[] testArray1 = {5, 1, 9, 3, 7};
        int[] expected1 = {1, 3, 5, 7, 9};
        quicksort(testArray1, 0, testArray1.length - 1);
        assert Arrays.equals(testArray1, expected1) : "Test 1 failed! Expected: " + Arrays.toString(expected1) + ", but got: " + Arrays.toString(testArray1);

        int[] testArray2 = {3, 3, 3, 3, 3};
        int[] expected2 = {3, 3, 3, 3, 3};
        quicksort(testArray2, 0, testArray2.length - 1);
        assert Arrays.equals(testArray2, expected2) : "Test 2 failed! Expected: " + Arrays.toString(expected2) + ", but got: " + Arrays.toString(testArray2);

        int[] testArray3 = {10, -3, 0, 5, 2, 8, -1};
        int[] expected3 = {-3, -1, 0, 2, 5, 8, 10};
        quicksort(testArray3, 0, testArray3.length - 1);
        assert Arrays.equals(testArray3, expected3) : "Test 3 failed! Expected: " + Arrays.toString(expected3) + ", but got: " + Arrays.toString(testArray3);

        System.out.println("All tests passed successfully!");
    }
}

/**
 * This algorithm differs from the one given in lectures because the pivot is selected between the left and right element essentially.
 * This is not a bad way of choosing the pivot however there are better methods.
 */
