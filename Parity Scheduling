import java.util.*;

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        
        System.out.print("Enter priority order (1 for higher number = higher priority, 2 for lower number = higher priority): ");
        int priorityOrder = sc.nextInt();

        int[] Process_id = new int[n];
        int[] Arrival_time = new int[n];
        int[] Burst_time = new int[n];
        int[] Priority = new int[n];
        int[] Remaining_time = new int[n];
        int[] Completion_time = new int[n];
        int[] Turnaround_time = new int[n];
        int[] Waiting_time = new int[n];
        int[] Response_time = new int[n];
        boolean[] isFirstResponse = new boolean[n];

        for (int i = 0; i < n; i++) {
            Process_id[i] = i + 1;
            System.out.print("Enter arrival time of P" + Process_id[i] + ": ");
            Arrival_time[i] = sc.nextInt();
            System.out.print("Enter burst time of P" + Process_id[i] + ": ");
            Burst_time[i] = sc.nextInt();
            System.out.print("Enter priority of P" + Process_id[i] + ": ");
            Priority[i] = sc.nextInt();
            Remaining_time[i] = Burst_time[i];
            isFirstResponse[i] = true;
        }

        int currentTime = 0, completed = 0;
        while (completed < n) {
            int idx = -1;
            int bestPriority = (priorityOrder == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (Arrival_time[i] <= currentTime && Remaining_time[i] > 0) {
                    if ((priorityOrder == 1 && Priority[i] > bestPriority) || (priorityOrder == 2 && Priority[i] < bestPriority)) {
                        bestPriority = Priority[i];
                        idx = i;
                    }
                }
            }

            if (idx == -1) {
                currentTime++;
            } else {
                if (isFirstResponse[idx]) {
                    Response_time[idx] = currentTime - Arrival_time[idx];
                    isFirstResponse[idx] = false;
                }

                Remaining_time[idx]--;
                currentTime++;

                if (Remaining_time[idx] == 0) {
                    Completion_time[idx] = currentTime;
                    Turnaround_time[idx] = Completion_time[idx] - Arrival_time[idx];
                    Waiting_time[idx] = Turnaround_time[idx] - Burst_time[idx];
                    completed++;
                }
            }
        }
        
        System.out.println("\nProcess\tAT\tBT\tPri\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + Process_id[i] + "\t" + Arrival_time[i] + "\t" + Burst_time[i] + "\t" + Priority[i] + "\t" + Completion_time[i] + "\t" + Turnaround_time[i] + "\t" + Waiting_time[i] + "\t" + Response_time[i]);
        }

        sc.close();
    }
}
