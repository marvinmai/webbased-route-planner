package de.unistuttgart;

import org.junit.Test;

public class DijkstraOneToAllTest {

    private Timer timer = new Timer();
    private MemoryMeasure memoryMeasure = new MemoryMeasure();

    private CoordinatesSet coordinatesSet;
    private AdjacencyArray adjacencyArray;

    private String basePath = System.getenv("ROUTE_PLANNER_BASE_PATH");
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

        DijkstraOneToAll dijkstraOneToAll = new DijkstraOneToAll();
        int[] distances = dijkstraOneToAll.calculateDistances(adjacencyArray,2);
        for(int i : distances) {
            System.out.println(i);
        }
    }

    @Test
    public void testGermany() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ALL");
        dataReader.readData(germanyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstraOneToAll = new DijkstraOneToAll();
        timer.start();
        System.out.println("calculating all distances...");
        dijkstraOneToAll.calculateDistances(adjacencyArray, 0);
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
        DijkstraOneToAll dijkstraOneToAll = new DijkstraOneToAll();
        timer.start();

        dijkstraOneToAll.calculateDistances(adjacencyArray,1);
        System.out.println("calculating all distances...");
        timer.stop();
        System.out.println("calculation finished.");
    }
}
