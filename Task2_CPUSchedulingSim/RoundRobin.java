import java.util.*;

public class RoundRobin extends Scheduler{
    final private int timeQuantum;

    public RoundRobin(List<Process> processes, int timeQuantum) {
        super(processes);
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void run() {
        // [1] Sort processes by arrival time
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime));
        List<Process> remainingProcesses = new ArrayList<>(processes);
        int currentTime = 0;

        // [2] Place proccesses into queue one by one
        Queue<Process> readyQueue = new LinkedList<>();

        // [3] While the ready queue or remaining is not empty:
        while (!readyQueue.isEmpty() || !remainingProcesses.isEmpty()) {

            // Add new processes to the ready queue
            enqueueProcesses(readyQueue, remainingProcesses, currentTime);

            // Handle Idle gap
            if (readyQueue.isEmpty()) {
                currentTime = remainingProcesses.get(0).arrivalTime;
                continue;
            }

            // Get the first process in the ready queue and run it
            Process currentP = readyQueue.poll();
            // Update start time once
            if (currentP.startTime < 0) {
                currentP.startTime = currentTime;
            }

            if (currentP.remainingTime <= timeQuantum) {
                printRunning(currentTime, currentP);
                currentTime += currentP.remainingTime;
                currentP.remainingTime = 0;
                calculateStats(currentTime, currentP);
                printCompleted(currentTime, currentP);
            } else { // Remaining time is longer than time quantum
                // Run processes
                printRunning(currentTime, currentP);
                currentTime += timeQuantum;
                currentP.remainingTime -= timeQuantum;

                // Find what process is next in ready queue at updated current time:
                enqueueProcesses(readyQueue, remainingProcesses, currentTime);

                printPause(currentTime, currentP);
                readyQueue.add(currentP);
            }
        }
        printStats(currentTime);
    }

    private void enqueueProcesses(Queue<Process> readyQueue, List<Process> remainingProcesses, int currentTime) {
        // Add new processes to the ready queue
        for (Process p: remainingProcesses) {
            if (p.arrivalTime <= currentTime)
            readyQueue.add(p);
        }

        // Remove those processes from the remaining processes list
        remainingProcesses.removeIf(p -> p.arrivalTime <= currentTime);
    }
}
