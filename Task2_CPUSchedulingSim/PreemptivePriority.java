import java.util.*;

public class PreemptivePriority extends Scheduler{
    
    public PreemptivePriority(List<Process> processes) {
        super(processes);
    }

    @Override
    public void run() {
        // Keep track of all remaining processes
        List<Process> remainingProcesses = new ArrayList<>();

        while (!remainingProcesses.isEmpty()) {
            
            // Find the list of proccesses currently avaiable to run

            // Sort the list by arrival time, then by priority

            // From that list, run the processes with the highest priority

            // If the arrival time of the next process does not overlap the current process neede time to burst, cpu idle.

            // Otherwise, the arrival time of the next process overlaps the time of the current process, jump to that arrival time and start that process
            // We will have to update the current processes stop time. 
    

        }
    }
}
