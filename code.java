import java.util.*;
public class CPUScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Scheduling Algorithm:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF (Non-Preemptive)");
        System.out.println("3. SJF (Preemptive)");
        System.out.println("4. Round Robin");
        System.out.println("5. Priority Scheduling");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        
        switch (choice) {
            case 1:
                fcfs(sc);
                break;
            case 2:
                sjfNonPreemptive(sc);
                break;
            case 3:
                sjfPreemptive(sc);
                break;
            case 4:
                roundRobin(sc);
                break;
            case 5:
                priorityScheduling(sc);
                break;
            default:
                System.out.println("Invalid choice!");
        }
        
        sc.close();
    }
    private static void displayResults(int n, int[] Process_id, int[] Arrival_time, int[] Burst_time, int[] Completion_time, int[] Turnaround_time, int[] Waiting_time, int[] Response_time) {
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + Process_id[i] + "\t" + Arrival_time[i] + "\t" + Burst_time[i] + "\t" + Completion_time[i] + "\t" + Turnaround_time[i] + "\t" + Waiting_time[i] + "\t" + Response_time[i]);
        }
    }
    private static void fcfs(Scanner sc) {
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        
        int Process_id[] = new int[n];
        int Arrival_time[] = new int[n];
        int Burst_time[] = new int[n];
        int Completion_time[] = new int[n];
        int Turnaround_time[] = new int[n];
        int Waiting_time[] = new int[n];
        int Response_time[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            Process_id[i] = i + 1;
            System.out.print("Enter arrival time of P" + Process_id[i] + ": ");
            Arrival_time[i] = sc.nextInt();
            System.out.print("Enter burst time of P" + Process_id[i] + ": ");
            Burst_time[i] = sc.nextInt();
        }

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
        for (int i = 0; i < n; i++) {
            if (currentTime < Arrival_time[i]) {
                currentTime = Arrival_time[i];
            }
            Completion_time[i] = currentTime + Burst_time[i];
            currentTime = Completion_time[i];
        }

        // Calculate Turnaround Time (TAT), Waiting Time (WT), Response Time (RT)
        for (int i = 0; i < n; i++) {
            Turnaround_time[i] = Completion_time[i] - Arrival_time[i];
            Waiting_time[i] = Turnaround_time[i] - Burst_time[i];
            Response_time[i] = Waiting_time[i]; // Since FCFS is non-preemptive
        }
        displayResults(n, Process_id, Arrival_time, Burst_time, Completion_time, Turnaround_time, Waiting_time, Response_time);
    }
    private static void sjfNonPreemptive(Scanner sc) {
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        
        int Process_id[] = new int[n];
        int Arrival_time[] = new int[n];
        int Burst_time[] = new int[n];
        int Completion_time[] = new int[n];
        int Turnaround_time[] = new int[n];
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
                Completion_time[idx] = currentTime + Burst_time[idx];
                currentTime = Completion_time[idx];

                // Calculate TAT, WT, and RT for the selected process
                Turnaround_time[idx] = Completion_time[idx] - Arrival_time[idx];
                Waiting_time[idx] = Turnaround_time[idx] - Burst_time[idx];
                Response_time[idx] = Waiting_time[idx]; // Since SJF is non-preemptive

                isCompleted[idx] = true;
                completed++;
            }
        }
        displayResults(n, Process_id, Arrival_time, Burst_time, Completion_time, Turnaround_time, Waiting_time, Response_time);
    }
    
    private static void sjfPreemptive(Scanner sc) {
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
        displayResults(n, Process_id, Arrival_time, Burst_time, Completion_time, Turnaround_time, Waiting_time, Response_time);
    }
    private static void roundRobin(Scanner sc) {
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
        displayResults(n, Process_id, Arrival_time, Burst_time, Completion_time, Turnaround_time, Waiting_time, Response_time);
    }
    private static void priorityScheduling(Scanner sc) {
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
        displayResults(n, Process_id, Arrival_time, Burst_time, Completion_time, Turnaround_time, Waiting_time, Response_time);
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
