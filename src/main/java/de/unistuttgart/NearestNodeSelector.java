package de.unistuttgart;

import java.util.ArrayList;
import java.util.List;

public class NearestNodeSelector {

    private DataSet dataSet;

    public NearestNodeSelector(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Node getForCoordinates(double clickLatitude, double clickLongitude) {
        double shortestDistance = Double.POSITIVE_INFINITY;
        double distance;
        Node nearestNode = null;

        List<Node> filteredNodes = getNodesInSquareAround(clickLatitude, clickLongitude, 0.1);

        for (Node node: filteredNodes) {

            distance = calcDistance(clickLatitude, clickLongitude, node.getLatitude(), node.getLongitude());

            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestNode = node;
            }
        }
        return nearestNode;
    }

    private double calcDistance(double lat1, double long1, double lat2, double long2) {
        return Math.pow(Math.abs(lat2 - lat1), 2) + Math.pow(Math.abs(long2 - long1), 2);
    }

    /**
     * Filtering nodes by putting a square around a given coordinate. The coordinate is located
     * in the middle of the square.
     *
     * @param latNode latitude of the coordinate
     * @param longNode longitude of the coordinate
     * @param squareSizeDegrees Side-length of the square in degrees. 0.1deg ~= 11.0574km
     * @return all nodes inside the given square
     */
    public List<Node> getNodesInSquareAround(double latNode, double longNode, double squareSizeDegrees) {
        List<Node> filteredNodes = new ArrayList<>();

        double lowerLong = longNode - squareSizeDegrees / 2;
        double upperLong = longNode + squareSizeDegrees / 2;
        double lowerLat =  latNode -  squareSizeDegrees / 2;
        double upperLat = latNode + squareSizeDegrees / 2;

        double[] currentNode;
        for (int i = 0; i < dataSet.getNodes().length; i++) {
            currentNode = dataSet.getNodes()[i];
            if (longitude(currentNode) > lowerLong && longitude(currentNode) < upperLong
                    && latitude(currentNode) > lowerLat && latitude(currentNode) < upperLat) {
                filteredNodes.add(new Node(i, latitude(currentNode), longitude(currentNode)));
            }
        }
        return filteredNodes;
    }

    private double latitude(double[] node) {
        return node[0];
    }

    private double longitude(double[] node) {
        return node[1];
    }
}
