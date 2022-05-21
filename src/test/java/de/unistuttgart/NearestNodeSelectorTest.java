package de.unistuttgart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NearestNodeSelectorTest {

    private Timer timer = new Timer();

    private CoordinatesSet coordinatesSet;

    private String toyPath = System.getenv("ROUTE_PLANNER_BASE_PATH") + "toy.fmi";
    private String germanyPath = System.getenv("ROUTE_PLANNER_BASE_PATH") + "germany.fmi";

    private DataReader dataReader = new DataReader();

    @Test
    public void getForCoordinatesTest() {
        System.out.println("************************************************************");
        System.out.println("START TEST: NEAREST COORDINATES SELECTOR");
        dataReader.readData(toyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        NearestNodeSelector nearestNodeSelector = new NearestNodeSelector(coordinatesSet);

        int nodeNumber = nearestNodeSelector.getForCoordinates(48.999592, 9.998788).getIndex();
        assertEquals(0, nodeNumber);

        nodeNumber = nearestNodeSelector.getForCoordinates(49.040000, 10.040000).getIndex();
        assertEquals(4, nodeNumber);

        nodeNumber = nearestNodeSelector.getForCoordinates(49.030000, 10.030000).getIndex();
        assertEquals(3, nodeNumber);

        nodeNumber = nearestNodeSelector.getForCoordinates(49.018899, 10.025564).getIndex();
        assertEquals(2, nodeNumber);
    }

    /**
     * For this test a node from the germany file was picked by hand.
     * In google maps a point near this node was selected and used
     * as the node, which will be selected by clicking in the map. The
     * node-id of the manually selected node is the expected value of each test.
     *
     */
    @Test
    public void getForCoordinatesTestGermany() {
        System.out.println("************************************************************");
        System.out.println("START TEST: NEAREST COORDINATES SELECTOR FOR GERMANY");
        System.out.println("reading data.....");
        timer.start();
        dataReader.readData(germanyPath);
        coordinatesSet = dataReader.getCoordinatesSet();
        System.out.println("Data reading finished.");
        timer.stop();

        // selected node: 1029 48.79474220000000173 8.96197910000000064
        System.out.println("calculating nearest node...");
        timer.start();
        NearestNodeSelector nearestNodeSelector = new NearestNodeSelector(coordinatesSet);
        int nodeNumber = nearestNodeSelector.getForCoordinates(48.742015, 12.736967).getIndex();
        System.out.println("Calculation finished.");
        timer.stop();
        assertEquals(1029, nodeNumber);


        // selected node: 41310 48.79474220000000173 8.96197910000000064
        System.out.println("calculating nearest node...");
        timer.start();
        nodeNumber = nearestNodeSelector.getForCoordinates(48.794742, 8.961979).getIndex();
        System.out.println("Calculation finished.");
        timer.stop();
        assertEquals(41310, nodeNumber);

        // this value was picked randomly in OSM and is checked in OSM afterwards. this test is therefor not really exact.
        // see test-documentation for screenshots and explanation: https://docs.google.com/document/d/1JVdAR7L2be2WpHK1Vw8FLtmbnCLe-jb4FFXxLl1d_Q0/edit?usp=sharing
        System.out.println("calculating nearest node...");
        timer.start();
        Node node = nearestNodeSelector.getForCoordinates(48.6104,8.4220);
        System.out.println("Calculation finished.");
        System.out.println("node found: " + node.getIndex() + " lat: " + node.getLatitude() + " long: " + node.getLongitude());
        timer.stop();
    }
}
