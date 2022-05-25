package de.unistuttgart;

public class CoordinatesSet {
    private int numberOfNodes;
    private int numberOfEdges;

    private double[][] coordinates;

    public CoordinatesSet(int numberOfNodes, int numberOfEdges) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfEdges = numberOfEdges;

        coordinates = new double[numberOfNodes][2];
    }

    public void addCoordinate(int nodeIndex, double latitude, double longitude) {
        coordinates[nodeIndex][0] = latitude;
        coordinates[nodeIndex][1] = longitude;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public double getLatitudeFor(int nodeId) {
        return coordinates[nodeId][0];
    }

    public double getLongitudeFor(int nodeId) {
        return coordinates[nodeId][1];
    }

}


