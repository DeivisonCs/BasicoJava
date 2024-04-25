package enums;

public enum ChangeColor {
    RED("\u001B[31m"),    // Task Stopped
    GREEN("\u001B[32m"),  // Task Completed
    YELLOW("\u001B[33m"), // Task Running
    BLUE("\u001B[34m"),   // Task A
    CYAN("\u001B[96m"),   // Task B
    PURPLE("\u001B[35m"), // Task C
    DEFAULT("\u001B[0m"); // Deafult Color

    private final String color;

    ChangeColor(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
