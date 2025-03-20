import java.util.*;
public class FCFS_Non_Preemptive {
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

        // Sorting of all according at  
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Arrival_time[i] > Arrival_time[j]) {
                    swap(Arrival_time, i, j);
                    swap(Burst_time, i, j);
                    swap(Process_id, i, j);
                }
            }
        }

        // Calculate CT with sorted at values
        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (currentTime < Arrival_time[i]) {
                currentTime = Arrival_time[i];
            }
            Compilation_time[i] = currentTime + Burst_time[i];
            currentTime = Compilation_time[i];
        }

        // Calculate Turnaround Time (TAT), Waiting Time (WT), Response Time (RT)
        for (int i = 0; i < n; i++) {
            Turn_arround_time[i] = Compilation_time[i] - Arrival_time[i];
            Waiting_time[i] = Turn_arround_time[i] - Burst_time[i];
            Response_time[i] = Waiting_time[i]; // Since FCFS is non-preemptive
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
