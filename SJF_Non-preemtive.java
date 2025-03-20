import java.util.*;

public class SJF_Non_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        
        int Process_id[] = new int[n];
        int Arrival_time[] = new int[n];
        int Burst_time[] = new int[n];
        int Compilation_time[] = new int[n];
        int Turn_arround_time[] = new int[n];
        int Waiting_time[] = new int[n];
        int Response_time[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            Process_id[i] = i + 1;
            System.out.print("Enter arrival time of P" + Process_id[i] + ": ");
            Arrival_time[i] = sc.nextInt();
            System.out.print("Enter burst time of P" + Process_id[i] + ": ");
            Burst_time[i] = sc.nextInt();
        }

        // Sort processes based on arrival time first
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Arrival_time[i] > Arrival_time[j]) {
                    swap(Arrival_time, i, j);
                    swap(Burst_time, i, j);
                    swap(Process_id, i, j);
                }
            }
        }

        int currentTime = 0;
        boolean[] isCompleted = new boolean[n];
        int completed = 0;

        while (completed < n) {
            // Find the process with the shortest burst time that has arrived
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && Arrival_time[i] <= currentTime && Burst_time[i] < minBurstTime) {
                    minBurstTime = Burst_time[i];
                    idx = i;
                }
            }

            // If no process has arrived yet, increment time
            if (idx == -1) {
                currentTime++;
            } else {
                Compilation_time[idx] = currentTime + Burst_time[idx];
                currentTime = Compilation_time[idx];

                // Calculate TAT, WT, and RT for the selected process
                Turn_arround_time[idx] = Compilation_time[idx] - Arrival_time[idx];
                Waiting_time[idx] = Turn_arround_time[idx] - Burst_time[idx];
                Response_time[idx] = Waiting_time[idx]; // Since SJF is non-preemptive

                isCompleted[idx] = true;
                completed++;
            }
        }

        // Print results
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + Process_id[i] + "\t" + Arrival_time[i] + "\t" + Burst_time[i] + "\t" + Compilation_time[i] + "\t" + Turn_arround_time[i] + "\t" + Waiting_time[i] + "\t" + Response_time[i]);
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
