// Read the users input and place into a list.

import java.io.*;
import java.util.*;

public class FileReader {
    public static List<Process> load(String filename) throws Exception{
        
        // Create empty list to fill
        List<Process> processes = new ArrayList<>();

        Scanner scanner = new Scanner(new File(filename));

        // Read each line in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            // Parse the file to get process things
            String[] processThing = line.split("\\s+");
            int pid =           Integer.parseInt(processThing[0]);
            int arrivalTime =   Integer.parseInt(processThing[1]);
            int burstTime =     Integer.parseInt(processThing[2]);
            int priority =      Integer.parseInt(processThing[3]);

            // Add a process to our processes list
            processes.add(new Process(pid, arrivalTime, burstTime, priority));

        }

        scanner.close();
        return processes;
    }
    
}