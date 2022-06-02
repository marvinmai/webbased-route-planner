package de.unistuttgart;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DijkstraOneToOneTest {

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
    public void testDijkstra() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE");
        dataReader.readData(toyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        int targetNode = 3;
        DijkstraOneToOne dijkstra = new DijkstraOneToOne(adjacencyArray, 0, targetNode);

        Iterable<double[]> path = dijkstra.pathTo(targetNode);
        List<double[]> expectedPath = new ArrayList<>();
        double node1 [] = {4.0, 3.0, 1.0};
        expectedPath.add(node1);
        double node2 [] = {0.0, 4.0, 7.0};
        expectedPath.add(node2);

        int i = 0;
        for(double[] e: path) {
            Assert.assertArrayEquals(expectedPath.get(i), e, 0.001);
            i++;
        }
    }

    @Test
    public void testDijkstra2() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE");
        memoryMeasure.start();
        dataReader.readData(toyPath2);
        coordinatesSet = dataReader.getCoordinatesSet();
        adjacencyArray= dataReader.getAdjacencyArray();

        int targetNode = 5;
        DijkstraOneToOne dijkstra = new DijkstraOneToOne(adjacencyArray, 0, targetNode);

        Iterable<double[]> path = dijkstra.pathTo(targetNode);
        List<double[]> expectedPath = new ArrayList<>();
        double node3 [] = {3.0, 5.0, 1.0};
        expectedPath.add(node3);
        double node1 [] = {4.0, 3.0, 1.0};
        expectedPath.add(node1);
        double node2 [] = {0.0, 4.0, 7.0};
        expectedPath.add(node2);

        memoryMeasure.stop();
        int i = 0;
        for(double[] e: path) {
            System.out.println("src: " + e[0] + " target: " + e[1] + " costs: " + e[2]);
            Assert.assertArrayEquals(expectedPath.get(i), e, 0.001);
            i++;
        }
    }

    @Test
    public void testDijkstraMV() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE - MV");

        memoryMeasure.start();

        System.out.println("reading data.....");
        timer.start();
        dataReader.readData(mvPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        System.out.println("Data reading finished.");
        timer.stop();
        timer.start();
        adjacencyArray= dataReader.getAdjacencyArray();
        int targetNode = 234232;
        System.out.println("calculating route.....");
        DijkstraOneToOne dijkstra = new DijkstraOneToOne(adjacencyArray, 0, targetNode);
        System.out.println("route calculation finished.....");
        timer.stop();


        Iterable<double[]> path = dijkstra.pathTo(targetNode);

        memoryMeasure.stop();

        for(double[] e: path) {
            System.out.println("src: " + e[0] + " target: " + e[1] + " costs: " + e[2]);
        }
    }

    @Test
    public void testDijkstraGermany() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE - GERMANY");
        memoryMeasure.start();
        System.out.println("reading data.....");
        timer.start();
        dataReader.readData(germanyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        System.out.println("Data reading finished.");
        timer.stop();
        timer.start();
        adjacencyArray= dataReader.getAdjacencyArray();
        int targetNode = 2414141;
        System.out.println("calculating route.....");
        DijkstraOneToOne dijkstra = new DijkstraOneToOne(adjacencyArray, 0, targetNode);

        Iterable<double[]> path = dijkstra.pathTo(targetNode);
        System.out.println("route calculation finished.....");
        timer.stop();
        memoryMeasure.stop();

        for(double[] e: path) {
            System.out.println("src: " + e[0] + " target: " + e[1] + " costs: " + e[2]);
        }
    }
}
