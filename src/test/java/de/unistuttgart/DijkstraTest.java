package de.unistuttgart;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DijkstraTest {

    private Timer timer = new Timer();

    private DataSet dataSet;
    private AdjacencyArray adjacencyArray;

    private String toyPath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/toy.fmi";
    private String germanyPath = "/home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi";

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
        double node2 [] = {4.0, 3.0, 1.0};
        expectedPath.add(node2);
        int i = 0;
        while(path.iterator().hasNext()) {
            assertEquals( (int) expectedPath.get(i)[0], (int) path.iterator().next()[0]);
            i++;
        }
    }
}
