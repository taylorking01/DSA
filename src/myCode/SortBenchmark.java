package myCode;

import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import java.util.Random;


public class SortBenchmark {

    private static ThreadMXBean thread_timer;
    private static long time = -1;

    // Call this to start timing.
    public static void start_timer() {
        if (time == -1) {
            time = thread_timer.getCurrentThreadCpuTime();
        }
        else {
            throw new Error();
        }
    }

    // Call this to stop timing. Returns time taken in nanonseconds.
    public static long stop_timer() {
        if (time == -1) {
            throw new Error();
        }
        else {
            long diff = thread_timer.getCurrentThreadCpuTime() - time;
            time = -1;
            return diff;
        }
    }

    // Random number generator for input arrays.
    private static Random r = new Random();

    public static Integer[] random_list(int n) {
        Integer[] a = new Integer[n];
        // TASK 2: Complete this function so it returns a list of n random integers.

        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt();
        }

        return a;
    }

    // Print out an array.
    public static void print_array(Object[] a) {
        for (int x = 0; x < a.length; x++) {
            System.out.print(a[x]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Do some initialisation.
        Integer[] a = null;
        thread_timer = ManagementFactory.getThreadMXBean();
        long time;
        r = new Random(20241024);  // Use any seed you like

        // Define the range of sizes to test (e.g., powers of 2).
        int[] sizes = {1000, 2000, 4000, 8000, 16000, 32000};

        // Benchmark Insertion Sort
        System.out.println("Benchmarking Insertion Sort...");
        for (int size : sizes) {
            a = random_list(size);
            start_timer();
            Insertion.sort(a);  // Call the Insertion Sort algorithm
            time = stop_timer();
            System.out.println("Insertion Sort," + size + "," + time);
        }

        // Reinitialize random number generator with the same seed for Merge Sort
        r = new Random(20241024);

        // Benchmark Merge Sort
        System.out.println("Benchmarking Merge Sort...");
        for (int size : sizes) {
            a = random_list(size);
            start_timer();
            MergeBU.sort(a);  // Call the Merge Sort algorithm
            time = stop_timer();
            System.out.println("Merge Sort," + size + "," + time);
        }

        // Reinitialize random number generator with the same seed for Quick Sort
        r = new Random(20241024);

        // Benchmark Quick Sort
        System.out.println("Benchmarking Quick Sort...");
        for (int size : sizes) {
            a = random_list(size);
            start_timer();
            Quick3way.sort(a);  // Call the Quick Sort algorithm
            time = stop_timer();
            System.out.println("Quick Sort," + size + "," + time);
        }

        // Reinitialize random number generator with the same seed for Shell Sort
        r = new Random(20241024);

        // Benchmark Shell Sort
        System.out.println("Benchmarking Shell Sort...");
        for (int size : sizes) {
            a = random_list(size);
            start_timer();
            Shell.sort(a);  // Call the Shell Sort algorithm
            time = stop_timer();
            System.out.println("Shell Sort," + size + "," + time);
        }
    }


}

// These sorting routines are adapted from code on Sedgewick's website:
// https://algs4.cs.princeton.edu/home/
// That code was distributed under the GPLv3. Therefore this code is also under GPLv3.

///////////////////////////////////////////////////////////////////////////////
// INSERTION SORT                                                            //
///////////////////////////////////////////////////////////////////////////////

class Insertion {

    // This class should not be instantiated.
    private Insertion() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}


///////////////////////////////////////////////////////////////////////////////
// MERGESORT                                                                 //
///////////////////////////////////////////////////////////////////////////////

class MergeBU {

    // This class should not be instantiated.
    private MergeBU() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unnecessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n-len; lo += len+len) {
                int mid  = lo+len-1;
                int hi = Math.min(lo+len+len-1, n-1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    /***********************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

}


///////////////////////////////////////////////////////////////////////////////
// QUICKSORT                                                                 //
///////////////////////////////////////////////////////////////////////////////

class Quick3way {

    // This class should not be instantiated.
    private Quick3way() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            Object swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    // quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }



    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}


///////////////////////////////////////////////////////////////////////////////
// SHELLSORT                                                                 //
///////////////////////////////////////////////////////////////////////////////

class Shell {

    // This class should not be instantiated.
    private Shell() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}