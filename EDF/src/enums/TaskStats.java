package enums;

public enum TaskStats {
    CREATED (ChangeColor.YELLOW.getColor() + "Created" + ChangeColor.DEFAULT.getColor()),
    READY (ChangeColor.YELLOW.getColor() + "Ready" + ChangeColor.DEFAULT.getColor()),
    START_RUNNING (ChangeColor.YELLOW.getColor() + "Starts Running" + ChangeColor.DEFAULT.getColor()),
    RUNNING ("Running"),
    PREEMPTED (ChangeColor.RED.getColor() + "Pre-empted" + ChangeColor.DEFAULT.getColor()),
    COMPLETED (ChangeColor.GREEN.getColor() + "Completed" + ChangeColor.DEFAULT.getColor())
    ;

    private String stats;

    private TaskStats(String stats){
        this.stats = stats;
    }
    
    public String getStats() {
        return stats;
    }
    public void setStats(String stats) {
        this.stats = stats;
    }
}
