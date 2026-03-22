// CS4310.03 - Operating Systems - Spring 2026
// Jeannette Ruiz - 018120531
// Project 1
// Task 1 - Multithreaded Sorting Application

import java.util.Arrays;

public class Multithreading {
    
    /* 
    Note for learning purposes:
    Global data is shared across all threads. 
    Create a global array. 
    */

    static final int[] unsortedArray = {7, 12, 19, 3, 18, 4, 2, 6, 15, 8};
    static int[] sortedArray = new int[unsortedArray.length];

    public static void main(String[] args) {
        
        // Mid section of our unsorted array.
        int mid = unsortedArray.length / 2;


        // THREAD 1: Sort first half
        Thread sortLeftThread = new SortThread(unsortedArray, 0, mid - 1);
        
        // THREAD 2: Sort second half
        Thread sortRightThread = new SortThread(unsortedArray, mid, unsortedArray.length - 1);

        // Start both Sorting threads
        sortLeftThread.start();
        sortRightThread.start();
        
        // Wait for sorting to finish before merging.
        try {
            sortLeftThread.join();
            sortRightThread.join();
        } catch (InterruptedException e) {}
        

        // THREAD 3: Merge arrays
        Thread mergeThreads = new MergeThread(unsortedArray, sortedArray, mid);

        // Wait for merging to finish to print the arrays
        mergeThreads.start();
        try {
            mergeThreads.join();
        } catch (InterruptedException e) {}


        //Print unsorted Array
        System.out.println("Unsorted Array: " + Arrays.toString(unsortedArray));
        //Print sorted Array
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
    }
}