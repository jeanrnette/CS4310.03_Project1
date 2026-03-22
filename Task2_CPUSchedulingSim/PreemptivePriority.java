import java.util.*;

public class PreemptivePriority extends Scheduler{
    
    public PreemptivePriority(List<Process> processes) {
        super(processes);
    }

    @Override
    public void run() {
        
        // [1] Sort by arrival time upfront
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime));

        List<Process> remainingProcesses = new ArrayList<>(processes);

        int currentTime = 0;


        while (!remainingProcesses.isEmpty()) {
            // [2] Get list of available processes at current time
            List<Process> availableProcesses = new ArrayList<>();
            for (Process p : remainingProcesses) {
                if (p.arrivalTime <= currentTime) {
                    availableProcesses.add(p);
                }
            }

            // [3] Handle idle gap
            if (availableProcesses.isEmpty()) {
                currentTime = remainingProcesses.get(0).arrivalTime;
                continue;
            }

            // [4] Pick the highest priority process available
            availableProcesses.sort(Comparator.comparingInt((Process p) -> p.priority));
            Process currentP = availableProcesses.get(0);
            printRunning(currentTime, currentP);

            
            // [5] Set the start time only once (Since preemptive, a process can be paused] and begin later.)
            if (currentP.startTime < 0) {
                currentP.startTime = currentTime;
            }
            
            // [6] From the remainng processes, find the first process that could preempt the current one
            int currentEndTime = currentP.remainingTime + currentTime;
            Process preemptP = null;
            for (Process p : remainingProcesses) {
                if (p.arrivalTime > currentTime
                    && p.arrivalTime < currentEndTime
                    && p.priority < currentP.priority) {
                        // Recall a smaller number = higher priority
                        preemptP = p;
                        break; // The second we find it, end this loop
                    }
            }

            // [7] If we find that preemptor process
            if (preemptP != null) {
                // This is the slice where the processes overlap
                int slice = preemptP.arrivalTime - currentTime;
                currentP.remainingTime -= slice;
                currentTime = preemptP.arrivalTime;
                printPause(currentTime, currentP);
                continue;
            }

            // [8] However, if we do not find a preemptor, we will complete the current process
            currentTime += currentP.remainingTime;
            calculateStats(currentTime, currentP);
            remainingProcesses.remove(currentP);
            printCompleted(currentTime, currentP);
        }
        printStats(currentTime);
    }
}
