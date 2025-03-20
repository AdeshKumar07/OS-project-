# OS-project
## Problem Statement
Different CPU scheduling algorithms have unique properties, and choosing the right one for a particular situation depends on various criteria used to compare their performance. These criteria are referred to as CPU scheduling criteria. For instance, in some cases, maximizing response time may be prioritized over throughput, while in others, minimizing average waiting time might be more important, even if it means sacrificing response time. Sometimes, a balance between multiple criteria is required. The challenge lies in determining which scheduling algorithm is best suited for a given situation. This project aims to address that challenge. The CPU Scheduling Simulator provides an environment where users can experimentally test and compare the performance of different scheduling algorithms through simulation. At the start, users can select any CPU scheduling algorithm they wish to evaluate. They then input the number of processes and relevant details for each process, such as CPU burst time, process ID, priority (if a priority scheduling policy is chosen), arrival time, and more. The simulator visually demonstrates the execution of processes, showing the remaining wait and burst times, as well as the processes waiting in the ready queue. One of the key features of this project is its Graphical User Interface, making it user-friendly and easy to operate.
## Features
 
- **Multiple Scheduling Algorithms**: Simulates various CPU scheduling algorithms such as FCFS, SJN, RR, and more.
- **User Interface**: Provides a graphical user interface (GUI) for easy interaction and visualization.
- **Detailed Output**: Displays the order of process execution, waiting time, turnaround time, and other performance metrics.
- **Custom Data Structures**: Tailored data structures for managing processes and scheduling tasks.
## Acknowledgements
 
- This simulator was inspired by standard CPU scheduling algorithms used in operating systems courses.
- Special thanks to the developers of the Java programming language for providing the necessary tools and frameworks.
