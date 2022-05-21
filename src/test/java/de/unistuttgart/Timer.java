package de.unistuttgart;

public class Timer {

    private double startTime;
    private double stopTime;

    public void start() {
        startTime = System.nanoTime() / 1000000;
    }

    public void stop() {
        stopTime = System.nanoTime() / 1000000;
        System.out.println("Execution duration: " + (stopTime - startTime) + "ms");
    }
}
