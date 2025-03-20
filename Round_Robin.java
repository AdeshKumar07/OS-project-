import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter the time quantum: ");
        int timeQuantum = sc.nextInt();

        int[] Process_id = new int[n];
        int[] Arrival_time = new int[n];
        int[] Burst_time = new int[n];
        int[] Remaining_burst_time = new int[n];
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
            Remaining_burst_time[i] = Burst_time[i];
            isFirstResponse[i] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        int currentTime = 0, completed = 0;
        boolean[] isInQueue = new boolean[n];

        while (completed < n) {
            for (int i = 0; i < n; i++) {
                if (Arrival_time[i] <= currentTime && !isInQueue[i] && Remaining_burst_time[i] > 0) {
                    queue.add(i);
                    isInQueue[i] = true;
                }
            }

            if (queue.isEmpty()) {
                currentTime++;
                continue;
            }

            int idx = queue.poll();

            if (isFirstResponse[idx]) {
                Response_time[idx] = currentTime - Arrival_time[idx];
                isFirstResponse[idx] = false;
            }

            int execTime = Math.min(timeQuantum, Remaining_burst_time[idx]);
            currentTime += execTime;
            Remaining_burst_time[idx] -= execTime;

            for (int i = 0; i < n; i++) {
                if (Arrival_time[i] <= currentTime && !isInQueue[i] && Remaining_burst_time[i] > 0) {
                    queue.add(i);
                    isInQueue[i] = true;
                }
            }

            if (Remaining_burst_time[idx] > 0) {
                queue.add(idx);
            } else {
                Completion_time[idx] = currentTime;
                Turnaround_time[idx] = Completion_time[idx] - Arrival_time[idx];
                Waiting_time[idx] = Turnaround_time[idx] - Burst_time[idx];
                completed++;
            }
        }

        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + Process_id[i] + "\t" + Arrival_time[i] + "\t" + Burst_time[i] + "\t" + Completion_time[i] + "\t" + Turnaround_time[i] + "\t" + Waiting_time[i] + "\t" + Response_time[i]);
        }

        sc.close();
    }
}
