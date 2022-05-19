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
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ALL");
        dataReader.readData(toyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstra = new DijkstraOneToAll(adjacencyArray);

        int[] result = dijkstra.getDistancesFromSrcToAll(0);

        printSolution(result);
    }

    @Test
    public void testGermany() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ALL");
        dataReader.readData(germanyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstra = new DijkstraOneToAll(adjacencyArray);
        timer.start();
        System.out.println("calculating all distances...");
        dijkstra.getDistancesFromSrcToAll(0);
        System.out.println("calculation finished.");
        timer.stop();
    }

    @Test
    public void testMV() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ALL");
        dataReader.readData(mvPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstra = new DijkstraOneToAll(adjacencyArray);
        timer.start();
        System.out.println("calculating all distances...");
        int[] result = dijkstra.getDistancesFromSrcToAll(0);
        System.out.println("calculation finished.");
        timer.stop();
        printSolution(result);
    }

    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

}
