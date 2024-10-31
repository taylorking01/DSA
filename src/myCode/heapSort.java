package myCode;

public class heapSort {


    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i +1;
        int right = 2 * i + 2;

        //Check if left child exists and is greater than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if the right child exists and is greater than the current largest
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not the root
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void heapsort(int[] arr) {
        int n = arr.length;

        //Build max heap#
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 1; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    };

    public static void main(String[] args) {
        int[] arr = {8,6,5,9,3,1,2};

        heapsort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

    }
}
