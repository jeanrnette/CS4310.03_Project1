import java.util.Arrays;

public class SortThread extends Thread {
    int[] array;
    int start, end;

    // Constructor to initialize the array and indices.
    SortThread (int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /*
    Note for learning purposes:3
    To make this class multithreadable, you have to override the thread classes run method.
     */
    @Override
    public void run() {
        // Note: Arrays.sort end is NOT inclusive
        Arrays.sort(array, start, end + 1);
    }
}