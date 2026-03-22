================================================================
CS4310.03 - Operating Systems(Spring 2026)
Jeannette Ruiz | Project 1
================================================================

PROJECT OVERVIEW

This project contains two tasks completed in Java:
  - Task 1 (20 pts): Multithreaded Sorting Application
  - Task 2 (80 pts): CPU Scheduling Simulator


================================================================
TASK 1 - Multithreaded Sorting Application

DESCRIPTION:
A multithreaded sorting program that sorts an integer array
using three threads:
  - Thread 1 (SortThread)  : Sorts the LEFT half of the array
  - Thread 2 (SortThread)  : Sorts the RIGHT half of the array
  - Thread 3 (MergeThread) : Merges both sorted halves into
                             a final sorted array

The unsorted array is a shared global array. Both sort threads
run concurrently, and the merge thread waits for both to finish
before merging using join().

SAMPLE OUTPUT:
  Unsorted Array: [7, 12, 19, 3, 18, 4, 2, 6, 15, 8]
  Sorted Array:   [2, 3, 4, 6, 7, 8, 12, 15, 18, 19]

HOW TO RUN:
Run with jar file:
java -jar MultithreadedSort.jar

NOTE: The input array is hardcoded in Multithreading.java.
      To change it, modify the unsortedArray variable.

FILE STRUCTURE:
  Task1_MultithreadedSortingApp/
  |-- MultithreadedSort.jar Executable jar file
  |-- Multithreading.java   Main class, defines global arrays
  |-- SortThread.java       Sorts a subarray using Arrays.sort()
  |-- MergeThread.java      Merges two sorted halves into one


================================================================
TASK 2 - CPU Scheduling Simulator

DESCRIPTION:
Simulates four CPU scheduling algorithms:
  1. FCFS - First Come First Serve
  2. SJF  - Shortest Job First (Non-Preemptive)
  3. PP   - Preemptive Priority Scheduling
  4. RR   - Round Robin

INPUT FILE FORMAT:
The program reads from a plain text file with the format:
  Pid  Arrival_Time  Burst_Time  Priority

Example input.txt:
  1  0  8  3
  2  1  4  1
  3  2  9  4
  4  3  5  2

  - Pid          : unique numeric process ID
  - Arrival_Time : time when task arrives (ms)
  - Burst_Time   : CPU time requested (ms)
  - Priority     : lower number = higher priority

HOW TO RUN:
Run with jar file:
   java -jar CPUSchedulingSim.jar
When prompted, enter the file name:
   File name: input.txt
   Or enter the full path if the file is elsewhere:
       File name: C:\Users\YourName\Desktop\input.txt
NOTE: An input.txt file is provided in the folder.
   You may replace its contents with your own processes
   following the format above.
Choose a scheduling algorithm (1-4).
   If Round Robin is chosen, enter a time quantum when prompted.

OUTPUT:
The program prints progress of each process and then:
  - Average Waiting Time
  - Average Response Time
  - Average Turnaround Time
  - CPU Utilization Rate

FILE STRUCTURE:
  Task2_CPUSchedulingSim/
  |-- CPUSchedulingSim.jar Executable jar file
  |-- CPUSchedulingSim.java    Main UI and menu
  |-- FileReader.java          Reads and parses input.txt
  |-- Process.java             Process data model
  |-- Scheduler.java           Abstract parent class
  |-- FCFS.java                First Come First Serve
  |-- SJF.java                 Shortest Job First
  |-- PreemptivePriority.java  Preemptive Priority Scheduling
  |-- RoundRobin.java          Round Robin
  |-- input.txt                Sample input file



================================================================