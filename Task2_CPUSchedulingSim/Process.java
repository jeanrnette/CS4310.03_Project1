// Process class
public class Process {
    // Process information
    public int pid;            // unique numeric process ID
    public int arrivalTime;    // time when task arrives
    public int burst;           // time task is requested by CPU
    public int priority;       // 1 = highest priority

    // Process Timing
    public int remainingTime;
    public int completionTime;

    public int waitTime = 0;
    public int responseTime = 0;
    public int turnaroundTime = 0;
    public int startTime = -1; // Start time when cpu addresses this process. This may not be equal to arrival time. 

    public Process(int pid, int arrivalTime, int burst, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burst = burst;
        this.priority = priority;
        this.remainingTime = burst;
    }
}
