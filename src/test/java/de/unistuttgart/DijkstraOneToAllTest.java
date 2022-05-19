package de.unistuttgart;

import org.junit.Test;

public class DijkstraOneToAllTest {

    private Timer timer = new Timer();
    private MemoryMeasure memoryMeasure = new MemoryMeasure();

    private CoordinatesSet coordinatesSet;
    private AdjacencyArray adjacencyArray;

    private String basePath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/";
    private String toyPath = basePath + "toy.fmi";
    private String toyPath2 = basePath + "toy-2.fmi";
    private String germanyPath = basePath + "germany.fmi";
    private String mvPath = basePath + "MV.fmi";

    private DataReader dataReader = new DataReader();

    @Test
    public void testToy() {

        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE");
        dataReader.readData(toyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstra = new DijkstraOneToAll();

        dijkstra.dijkstra(adjacencyArray, 0);
    }

}
