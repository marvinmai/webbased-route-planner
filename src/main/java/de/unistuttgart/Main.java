package de.unistuttgart;

public class Main {

    private String basePath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/";
    private String toyPath = basePath + "toy.fmi";
    private String toyPath2 = basePath + "toy-2.fmi";
    private String germanyPath = basePath + "germany.fmi";
    private String mvPath = basePath + "MV.fmi";

    private DataReader dataReader = new DataReader();
    public void main(String[] args) {
        long start = System.nanoTime() / 1000000;

        dataReader.readData(toyPath);
        CoordinatesSet coordinatesSet = dataReader.getCoordinatesSet();

        long duration = System.nanoTime() / 1000000 - start;
        System.out.println("Execution duration in ms: " + duration);
    }
}