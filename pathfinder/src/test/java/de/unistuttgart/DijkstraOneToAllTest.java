package de.unistuttgart;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class DijkstraOneToAllTest {

    private Timer timer = new Timer();
    private MemoryMeasure memoryMeasure = new MemoryMeasure();

    private CoordinatesSet coordinatesSet;
    private AdjacencyArray adjacencyArray;

    private String basePath = System.getenv("ROUTE_PLANNER_BASE_PATH");
    private String toyPath = basePath + "toy.fmi";
    private String toyPath2 = basePath + "toy-2.fmi";
    private String myTestPath = basePath + "myTest.fmi";
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
        int res[] = dijkstraOneToAll.calculateDistances(adjacencyArray,0);
        for(int i = 0; i < res.length; i++){
            System.out.println("From " + 0 + " To " + i + " " + res[i]);
        }


    }
    @Test
    public void myTest(){
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ALL");
        dataReader.readData(myTestPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstraOneToAll = new DijkstraOneToAll();
        int res[] = dijkstraOneToAll.calculateDistances(adjacencyArray,0);
         for(int i = 0; i < res.length; i++){
            System.out.println("From " + 0 + " To " + i + " " + res[i]);
        }



    }


    @Test
    public void testGermany() throws IOException {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ALL");
        dataReader.readData(germanyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        DijkstraOneToAll dijkstraOneToAll = new DijkstraOneToAll();
        timer.start();
        System.out.println("calculating all distances...");
        int res[] = dijkstraOneToAll.calculateDistances(adjacencyArray, 638394);
        System.out.println(res[7654321]);

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
        int res[] = dijkstraOneToAll.calculateDistances(adjacencyArray, 638394);
        System.out.println(res[7654321]);
        dijkstraOneToAll.calculateDistances(adjacencyArray,1);
        System.out.println("calculating all distances...");

        timer.stop();
        System.out.println("calculation finished.");
    }
}
