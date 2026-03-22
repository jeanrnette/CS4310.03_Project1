// CS4310.03 - Operating Systems - Spring 2026
// Jeannette Ruiz - 018120531
// Project 1
// Task 2 - CPU Scheduling Simulator
import java.util.*;

public class CPUSchedulingSim {
    
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        // Print Program Name
        System.out.println("--------------------------------");
        System.out.println("--- CPU SCHEDULING SIMULATOR ---");
        System.out.println("--------------------------------");
        // Print what program does
        System.out.println("This program simulates a CPU scheduling algorithm.");

        // Prompt user to enter file name
        System.out.println("\nEnter the path to your input file.");System.out.println("File must follow format: Pid Arrival_Time Burst_Time Priority");
        System.out.println("Example: input.txt or C:\\Users\\user\\input.txt");
        System.out.print("File name: ");

        // Get file and parse
        String filename = scanner.nextLine().trim();
        try { 
            processes = FileReader.load(filename);
            System.out.println("\nYour file contains " + processes.size() + " processes.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            System.out.println("Please make sure the file exists and try again.");
            scanner.close();
            return;
        }
        
        // Ask user to choose a scheduling algoirthm
        System.out.println("\nChoose a sorting algorithm: \n[1] First Come First Serve (FCFS) \n[2] Shortest Job First (SJF) \n[3] Preemptive Priorty Scheduling \n[4] Round Robin");

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Start FCFS
                FCFS fcfs = new FCFS(processes);
                fcfs.run();
                break;
            case 2:
                SJF sjf = new SJF(processes);
                sjf.run();
                break;
            case 3: 
                PreemptivePriority pp = new PreemptivePriority(processes);
                pp.run();
                break;
            case 4: 
                
                break;
            default:
                System.out.println("deafult");
        }
        
        scanner.close();
    }
}
