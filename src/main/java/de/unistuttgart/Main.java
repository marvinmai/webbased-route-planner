package de.unistuttgart;

public class Main {

    private static DataReader dataReader = new DataReader();
    public static void main(String[] args) {
        long start = System.nanoTime() / 1000000;

        String filePath = "/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi";
        dataReader.readData(filePath);
        CoordinatesSet coordinatesSet = dataReader.getCoordinatesSet();

        long duration = System.nanoTime() / 1000000 - start;
        System.out.println("Execution duration in ms: " + duration);
    }
}