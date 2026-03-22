import java.util.*;

public abstract class Scheduler {
    List<Process> processes;
    
    public Scheduler (List<Process> processes) {
        this.processes = new ArrayList<>(processes);
    }

    // All algorithms have to handle idle gaps
    public int handleIdleGap(int currentTime, int arrivalTime) {
        // If there is a gap, move the clock foward to arrival time.
        if (currentTime < arrivalTime) {
            System.out.println(currentTime + "ms-" + arrivalTime + "ms : CPU Idle");
            return arrivalTime;
        }
        // If there is NO gap, return the currentTime.
        return currentTime;
    }
    
    public abstract void run();

    // printRunning(): Start running a process.
    public void printRunning(int currentTime, Process p) {
        // Time # : Process # running (#ms remaining)
        System.out.println("Time " + currentTime + " : Process " + p.pid + " running (" + p.remainingTime + "ms remaining)");
    }

    // printPause(): Pause a process (preemptive)
    public void printPause(int currentTime, Process p) {
        // Time # : Process # running (#ms remaining)
        System.out.println("Time " + currentTime + " : Process " + p.pid + " paused (" + p.remainingTime + "ms remaining)");
    }

    //printComplete(): Complete a process
    public void printCompleted(int currentTime, Process p) {
        System.out.println("Time " + currentTime + " : Process " + p.pid + " COMPLETED");
    }

    // updateStats()
    public void calculateStats(int currentTime, Process p) {
        p.completionTime    = currentTime;
        p.turnaroundTime    = p.completionTime - p.arrivalTime;
        p.waitTime          = p.turnaroundTime - p.burst;
        p.responseTime      = p.startTime - p.arrivalTime;
    }

    // printStats(): Print out stats regarding averages and CPU utilization.
    public void printStats(int totalTime) {
        double totalWaitTime = 0.0, totalResponseTime = 0.0, totalTurnaroundTime = 0.0, totalBurst = 0.0;

        // Total amount of processes
        int n = processes.size();

        for (Process p : processes) {
            totalWaitTime += p.waitTime;
            totalResponseTime += p.responseTime;
            totalTurnaroundTime += p.turnaroundTime;
            totalBurst += p.burst;
        }

        System.out.println("Average Wait Time: " + (totalWaitTime / n) + "ms");
        System.out.println("Average Response Time: " + (totalResponseTime / n) + "ms");
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n) + "ms");
        System.out.println("CPU UtilizationRate: " + (totalBurst / totalTime * 100) + "%");

    }

}
