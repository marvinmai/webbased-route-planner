package de.unistuttgart;

public class MemoryMeasure {

    Runtime runtime;
    long memoryBefore;

    public void start() {
        System.gc();
        runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
    }

    public void stop() {
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total memory used: " + (memoryAfter - memoryBefore)/1000000 + " Mbytes");
    }
}
