package de.unistuttgart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NearestNodeSelectorTest {

    private Timer timer = new Timer();

    private DataSet dataSet;

    private String toyPath = "/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/toy.fmi";
    private String germanyPath = "/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi";

    @Test
    public void getForCoordinatesTest() {
        System.out.println("************************************************************");
        System.out.println("START TEST: NEAREST COORDINATES SELECTOR");
        dataSet = DataSetReader.readDataSet(toyPath);
        NearestNodeSelector nearestNodeSelector = new NearestNodeSelector(dataSet);

        int nodeNumber = nearestNodeSelector.getForCoordinates(48.999592, 9.998788);
        assertEquals(0, nodeNumber);

        nodeNumber = nearestNodeSelector.getForCoordinates(49.040000, 10.040000);
        assertEquals(4, nodeNumber);

        nodeNumber = nearestNodeSelector.getForCoordinates(49.030000, 10.030000);
        assertEquals(3, nodeNumber);

        nodeNumber = nearestNodeSelector.getForCoordinates(49.018899, 10.025564);
        assertEquals(2, nodeNumber);
    }

    @Test
    public void getForCoordinatesTestGermany() {
        System.out.println("************************************************************");
        System.out.println("START TEST: NEAREST COORDINATES SELECTOR FOR GERMANY");
        System.out.println("reading data.....");
        dataSet = DataSetReader.readDataSet(germanyPath);
        System.out.println("Data reading finished.");
        System.out.println("calculating next node...");
        timer.start();
        NearestNodeSelector nearestNodeSelector = new NearestNodeSelector(dataSet);

        int nodeNumber = nearestNodeSelector.getForCoordinates(48.742015, 12.736967);
        System.out.println("Calculation finished.");
        timer.stop();
        assertEquals(1029, nodeNumber);
    }
}
