package de.unistuttgart;

import java.util.ArrayList;
import java.util.List;

public class NearestCoordinateSelector {

    private CoordinatesSet coordinatesSet;

    public NearestCoordinateSelector(CoordinatesSet coordinatesSet) {
        this.coordinatesSet = coordinatesSet;
    }

    public Coordinate getForCoordinates(double clickLatitude, double clickLongitude) {
        double shortestDistance = Double.POSITIVE_INFINITY;
        double distance;
        Coordinate nearestCoordinate = null;

        List<Coordinate> filteredCoordinates = getNodesInSquareAround(clickLatitude, clickLongitude, 0.1);

        for (Coordinate coordinate : filteredCoordinates) {

            distance = calcDistance(clickLatitude, clickLongitude, coordinate.getLatitude(), coordinate.getLongitude());

            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestCoordinate = coordinate;
            }
        }
        return nearestCoordinate;
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
    private List<Coordinate> getNodesInSquareAround(double latNode, double longNode, double squareSizeDegrees) {
        List<Coordinate> filteredCoordinates = new ArrayList<>();

        double lowerLong = longNode - squareSizeDegrees / 2;
        double upperLong = longNode + squareSizeDegrees / 2;
        double lowerLat =  latNode -  squareSizeDegrees / 2;
        double upperLat = latNode + squareSizeDegrees / 2;

        double[] currentNode;
        for (int i = 0; i < coordinatesSet.getCoordinates().length; i++) {
            currentNode = coordinatesSet.getCoordinates()[i];
            if (longitude(currentNode) > lowerLong && longitude(currentNode) < upperLong
                    && latitude(currentNode) > lowerLat && latitude(currentNode) < upperLat) {
                filteredCoordinates.add(new Coordinate(i, latitude(currentNode), longitude(currentNode)));
            }
        }
        return filteredCoordinates;
    }

    private double latitude(double[] node) {
        return node[0];
    }

    private double longitude(double[] node) {
        return node[1];
    }
}
