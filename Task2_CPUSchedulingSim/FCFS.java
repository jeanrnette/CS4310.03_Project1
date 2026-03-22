import java.util.*;

public class FCFS extends Scheduler {

    public FCFS (List<Process> processes) {
        super(processes);
    }

    @Override
    public void run() {
        
        // Sort the processes
        processes.sort(
            // First sort by arrival Time
            Comparator.comparingInt((Process p) -> p.arrivalTime)
            // Then break any ties by sorting by priority
            .thenComparingInt((Process p) -> p.priority)
        );


        int currentTime = 0; // Time Tracker

        for (Process p : processes) {
            // Handling idle gaps.
            currentTime = handleIdleGap(currentTime, p.arrivalTime);

            // Update the processes start time to the current time (a process may arrive at 0 but be handled by CPU at time 3)
            p.startTime = currentTime;
            
            // Print: current process running
            printRunning(currentTime, p);
            currentTime += p.burst;

            // Update a process stats:
            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitTime = p.turnaroundTime - p.burst;
            p.responseTime = p.startTime - p.arrivalTime;

            // Print: current process complete
            printCompleted(currentTime, p);
        }

        printStats(currentTime);

    }
}