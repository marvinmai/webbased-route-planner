package de.unistuttgart;

import org.junit.Test;

public class DijkstraOneToAllTest {

    private Timer timer = new Timer();
    private MemoryMeasure memoryMeasure = new MemoryMeasure();

    private CoordinatesSet coordinatesSet;
    private AdjacencyArray adjacencyArray;

    private String basePath = "/Users/aminheidari/Documents/Uni/Programmierprojekt/Phase I/";
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

        //DijkstraOneToAll dijkstra = new DijkstraOneToAll(adjacencyArray);

        //int[] result = dijkstra.getDistancesFromSrcToAll(0);

        WorkingOneToAllDijkstra workingOneToAllDijkstra = new WorkingOneToAllDijkstra();
        workingOneToAllDijkstra.dijktstra(adjacencyArray,2);
        for(int i : workingOneToAllDijkstra.getDist()) {
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

        WorkingOneToAllDijkstra workingOneToAllDijkstra = new WorkingOneToAllDijkstra();
        timer.start();
        System.out.println("calculating all distances...");
        workingOneToAllDijkstra.dijktstra(adjacencyArray, 0);
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
        WorkingOneToAllDijkstra workingOneToAllDijkstra = new WorkingOneToAllDijkstra();
        //DijkstraOneToAll dijkstra = new DijkstraOneToAll(adjacencyArray);
        timer.start();

        workingOneToAllDijkstra.dijktstra(adjacencyArray,1);
        System.out.println("calculating all distances...");
        timer.stop();
        System.out.println("calculation finished.");
        int counter = 0;


    }

    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

}
