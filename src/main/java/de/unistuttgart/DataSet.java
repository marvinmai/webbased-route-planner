package de.unistuttgart;

public class DataSet {
    private int numberOfNodes;
    private int numberOfEdges;

    private double[][] coordinates;

    public DataSet() {

    }

    public void init(int numberOfNodes, int numberOfEdges) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfEdges = numberOfEdges;

        coordinates = new double[numberOfNodes][2];
    }

    public void addCoordinate(int index, double latitude, double longitude) {
        coordinates[index][0] = latitude;
        coordinates[index][1] = longitude;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }
}


