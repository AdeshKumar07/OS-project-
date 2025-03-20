import java.util.*;
public class SJF_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        
        int Process_id[] = new int[n];
        int Arrival_time[] = new int[n];
        int Burst_time[] = new int[n];
        int Remaining_burst_time[] = new int[n];  // To track remaining burst time
        int Completion_time[] = new int[n];
        int Turnaround_time[] = new int[n];
        int Waiting_time[] = new int[n];
        int Response_time[] = new int[n];
        int firstExecution[] = new int[n]; // To track first execution time
        
        for (int i = 0; i < n; i++) {
            Process_id[i] = i + 1;
            System.out.print("Enter arrival time of P" + Process_id[i] + ": ");
            Arrival_time[i] = sc.nextInt();
            System.out.print("Enter burst time of P" + Process_id[i] + ": ");
            Burst_time[i] = sc.nextInt();
            Remaining_burst_time[i] = Burst_time[i];  // Initialize remaining burst time
            firstExecution[i] = -1; // Initially, no process has started execution
        }

        // Sort processes based on arrival time first
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Arrival_time[i] > Arrival_time[j]) {
                    swap(Arrival_time, i, j);
                    swap(Burst_time, i, j);
                    swap(Process_id, i, j);
                    swap(Remaining_burst_time, i, j);
                }
            }
        }

        int currentTime = 0;
        boolean[] isCompleted = new boolean[n];
        int completed = 0;

        while (completed < n) {
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;

            // Find the process with the shortest remaining burst time that has arrived
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && Arrival_time[i] <= currentTime) {
                    if (Remaining_burst_time[i] < minBurstTime || (Remaining_burst_time[i] == minBurstTime && Arrival_time[i] < Arrival_time[idx])) {
                        minBurstTime = Remaining_burst_time[i];
                        idx = i;
                    }
                }
            }

            if (idx == -1) {
                currentTime++; // If no process has arrived yet, increment time
            } else {
                // If this is the first time the process is executing, record its start time
                if (firstExecution[idx] == -1) {
                    firstExecution[idx] = currentTime;
                }

                // Execute the selected process
                Remaining_burst_time[idx]--;
                currentTime++;

                // If the process is finished, calculate completion time, TAT, WT, and RT
                if (Remaining_burst_time[idx] == 0) {
                    isCompleted[idx] = true;
                    completed++;
                    Completion_time[idx] = currentTime;

                    Turnaround_time[idx] = Completion_time[idx] - Arrival_time[idx];
                    Waiting_time[idx] = Turnaround_time[idx] - Burst_time[idx];
                    Response_time[idx] = firstExecution[idx] - Arrival_time[idx]; // Calculate response time
                }
            }
        }

        // Print results
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + Process_id[i] + "\t" + Arrival_time[i] + "\t" + Burst_time[i] + "\t" + Completion_time[i] + "\t" + Turnaround_time[i] + "\t" + Waiting_time[i] + "\t" + Response_time[i]);
        }

        sc.close();
    }

    // Swap function for sorting
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
