package myCode;

public class heapSort {


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void heapsort(int[] arr) {
        int n = arr.length;

        //Build max heap#
        for (int i = n / 2 - 1; i >= 0; i--) {
            //heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 1; i--) {
            //swap(arr, 0, i);
            //heapify(arr, i, 0);
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
