package de.unistuttgart;

public class DataSet {
    private int numberOfNodes;
    private int numberOfEdges;

    private double[][] coordinates;

    private int[] sourceNodeIds;
    private int[] targetNodeIds;
    private int[] sourceToTargetCosts;

    public DataSet() {

    }

    public void init(int numberOfNodes, int numberOfEdges) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfEdges = numberOfEdges;

        coordinates = new double[numberOfNodes][2];

        sourceNodeIds = new int[numberOfEdges];
        targetNodeIds = new int[numberOfEdges];
        sourceToTargetCosts = new int[numberOfEdges];
    }

    public void addCoordinate(int index, double latitude, double longitude) {
        coordinates[index][0] = latitude;
        coordinates[index][1] = longitude;
    }

    public void addEntry(int index, int sourceNodeId, int targetNodeId, int sourceToTargetCost) {
        sourceNodeIds[index] = sourceNodeId;
        targetNodeIds[index] = targetNodeId;
        sourceToTargetCosts[index] = sourceToTargetCost;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }
}


