package de.unistuttgart;

import org.junit.Test;

public class DataReaderTest {

    private static DataReader dataReader = new DataReader();

    private String basePath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/";
    private String toyPath = basePath + "toy.fmi";
    private String toyPath2 = basePath + "toy-2.fmi";
    private String germanyPath = basePath + "germany.fmi";
    private String mvPath = basePath + "MV.fmi";

    private Timer timer = new Timer();

    @Test
    public void testDataReader() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE");
        timer.start();

        dataReader.readData(toyPath);
        CoordinatesSet coordinatesSet = dataReader.getCoordinatesSet();

        timer.stop();
    }

}
