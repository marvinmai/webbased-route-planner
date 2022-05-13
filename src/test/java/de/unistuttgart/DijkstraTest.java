package de.unistuttgart;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DijkstraTest {

    private Timer timer = new Timer();

    private DataSet dataSet;
    private AdjacencyArray adjacencyArray;

    private String toyPath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/toy.fmi";
    private String toyPath2 = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/toy-2.fmi";
    private String germanyPath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi";
    private String mvPath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/MV.fmi";

    private DataSetReader dataSetReader = new DataSetReader();

    @Test
    public void testDijkstra() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE");
        dataSet = dataSetReader.readDataSet(toyPath);
        adjacencyArray= dataSetReader.getAdjacencyArray();


        Dijkstra dijkstra = new Dijkstra(adjacencyArray, 0);
        int targetNode = 3;
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
        dataSet = dataSetReader.readDataSet(toyPath2);
        adjacencyArray= dataSetReader.getAdjacencyArray();


        Dijkstra dijkstra = new Dijkstra(adjacencyArray, 0);
        int targetNode = 5;
        Iterable<double[]> path = dijkstra.pathTo(targetNode);
        List<double[]> expectedPath = new ArrayList<>();
        double node3 [] = {3.0, 5.0, 1.0};
        expectedPath.add(node3);
        double node1 [] = {4.0, 3.0, 1.0};
        expectedPath.add(node1);
        double node2 [] = {0.0, 4.0, 7.0};
        expectedPath.add(node2);

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
        System.out.println("reading data.....");
        timer.start();
        dataSet = dataSetReader.readDataSet(mvPath);
        System.out.println("Data reading finished.");
        timer.stop();
        timer.start();
        adjacencyArray= dataSetReader.getAdjacencyArray();

        System.out.println("calculating route.....");
        Dijkstra dijkstra = new Dijkstra(adjacencyArray, 0);
        System.out.println("route calculation finished.....");
        timer.stop();

        int targetNode = 5;
        Iterable<double[]> path = dijkstra.pathTo(targetNode);

        for(double[] e: path) {
            System.out.println("src: " + e[0] + " target: " + e[1] + " costs: " + e[2]);
        }
    }

    @Test
    public void testDijkstraGermany() {
        System.out.println("************************************************************");
        System.out.println("TESTING DIJKSTRA PATH ONE-TO-ONE - GERMANY");
        System.out.println("reading data.....");
        timer.start();
        dataSet = dataSetReader.readDataSet(germanyPath);
        System.out.println("Data reading finished.");
        timer.stop();
        timer.start();
        adjacencyArray= dataSetReader.getAdjacencyArray();

        System.out.println("calculating route.....");
        Dijkstra dijkstra = new Dijkstra(adjacencyArray, 0);
        int targetNode = 5;
        Iterable<double[]> path = dijkstra.pathTo(targetNode);
        System.out.println("route calculation finished.....");
        timer.stop();

        for(double[] e: path) {
            System.out.println("src: " + e[0] + " target: " + e[1] + " costs: " + e[2]);
        }
    }
}
