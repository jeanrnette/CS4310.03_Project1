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

        
        // Prompt user on what the program does
        System.out.println("This program simulates a CPU scheduling algorithm.");

        // Recieve text file from user
        System.out.println("Input your file name: ");
        String filename = scanner.nextLine().trim();
        try { 
            processes = FileReader.load(filename);
            System.out.println("Your file contains " + processes.size() + " processes.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        
       
         // Ask user to choose a scheduling algoirthm
        System.out.println("Choose a sorting algorithm: \n[1] First Come First Serve (FCFS) \n[2] Shortest Job First (SJF) \n[3] Preemptive Priorty Scheduling \n[4] Round Robin");

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Start FCFS
                FCFS fcfs = new FCFS(processes);
                fcfs.run();
                break;
            case 2:
                
                break;
            case 3: 
                
                break;
            case 4: 
                
                break;
            default:
                System.out.println("deafult");
        }
        
        scanner.close();
    }
}
