package de.unistuttgart;

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

        List<Node> filteredNodes = dataSet.getNodesInSquareAround(clickLatitude, clickLongitude, 0.1);

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

}
