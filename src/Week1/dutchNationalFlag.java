package Week1;

public class dutchNationalFlag {
    public static void partition(int[] A, int m) {
        int low = 0; //Tracks elements less than m
        int mid = 0; //Tracks current element being checked
        int high = A.length - 1; //Tracks elements greater than m

        while (mid < high) {
            if (A[mid] < m) {
                swap(A, low, mid); //Swap elements at low and mid
                low++; //Move low pointer forward
                mid++; //Move mid pointer forward
            } else if (A[mid] > m) {
                swap(A, mid, high); //Swap elements at mid and high
                high--; //Move high pointer backward
                //Do not move mid since A[mid] needs to be rechecked
            } else { //A[mid] is equal to m
                mid++; //Move mid forward
            }
        }
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void printArray(int[] A) {
        for (int num : A) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = {3,9,2,2,8,5,3,7,6,3,5,2};
        int pivot = 5; //Write some function to get pivot

        System.out.println("Original array: ");
        printArray(A);

        partition(A, pivot);

        System.out.println("Partitioned array: ");
        printArray(A);
    }
}
