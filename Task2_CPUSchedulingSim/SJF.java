import java.util.*;

public class SJF extends Scheduler {

    public SJF (List<Process> processes) {
        super(processes);
    }

    @Override
    public void run() {
        // Sort by arrival time upfront
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime));

        // Keep track of process that have arrived at current time
        List<Process> remainingProcesses = new ArrayList<>(processes);

        int currentTime = 0; // Time Tracker

        while (!remainingProcesses.isEmpty()) {
            
            // [1] Find processes that have arrived by current time
            List<Process> availableProcesses = new ArrayList<>();
            for (Process p : remainingProcesses) {
                // If the arrival time is smaller than the current time, it is available for CPU
                if (p.arrivalTime <= currentTime) {
                    availableProcesses.add(p);
                }
            }

            // If nothing has arrived, jump to next processes arrival time and search for processes again
            if (availableProcesses.isEmpty()) {
                currentTime = remainingProcesses.get(0).arrivalTime;
                continue;
            }


            // [2] Sort the available processes by the shortest burst time, break tie by 
            availableProcesses.sort(
            Comparator.comparingInt((Process p) -> p.burst).thenComparingInt((Process p) -> p.priority).
            thenComparingInt((Process p) -> p.arrivalTime));

            // Get the current processs
            Process currentProcess = availableProcesses.get(0);
            currentProcess.startTime = currentTime;

            // [3] Run the current process
            printRunning(currentTime, currentProcess);
            currentTime += currentProcess.burst;
            calculateStats(currentTime, currentProcess);

            // [4] Remove the current process from remaining and print complete
            remainingProcesses.remove(currentProcess);
            printCompleted(currentTime, currentProcess);
        }
        //Print the average stats
        printStats(currentTime);
    }
}