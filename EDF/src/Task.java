import enums.TaskStats;

public class Task {
    String name;
    int arrivalTime;
    int deadline;
    int period;
    int executionTime;
    int remainingTime;
    int periodInitialTime;
    int initialDeadline;
    TaskStats STATS;

    public Task(String name, int arrivalTime, int deadline, int executionTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.deadline = arrivalTime + deadline;
        this.period = deadline;
        this.periodInitialTime = arrivalTime;
        this.initialDeadline = deadline;
        this.executionTime = executionTime;
        this.remainingTime = executionTime;
        this.STATS = TaskStats.CREATED;
    }
}