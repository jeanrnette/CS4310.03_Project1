// This class serves to merge two sublists in an original list into a new list of the same size.
public class MergeThread extends Thread {
    // Instance variables
    public int[] unsorted, sorted;
    public int mid;

    public MergeThread(int[] unsorted, int[] sorted, int mid) {
        this.unsorted = unsorted;
        this.sorted = sorted;
        this.mid = mid;
    }
    
    @Override
    public void run() {
        // i = start of first half
        // j = start of second half / mid point
        // k to copy value into new sorted list
        int i = 0, j = mid, k = 0;

        while (i < mid && j < unsorted.length) {
            if (unsorted[i] < unsorted[j]) {
                sorted[k++] = unsorted[i++];
            }
            else {
                sorted[k++] = unsorted[j++];
            }
        }

        // If first half is fininshed before second, we must copy second half only now
        while (j < unsorted.length) sorted[k++] = unsorted[j++];
        // If second half is finished before first, we must copy first half only now
        while (i < mid) sorted[k++] = unsorted[i++];
    }
}