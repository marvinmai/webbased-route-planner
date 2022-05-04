package de.unistuttgart;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private int numberOfNodes;
    private int numberOfEdges;

    private double[][] nodes;

    private int[] sourceNodeIds;
    private int[] targetNodeIds;
    private int[] sourceToTargetCosts;

    public DataSet() {

    }

    public void init(int numberOfNodes, int numberOfEdges) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfEdges = numberOfEdges;

        nodes = new double[numberOfNodes][2];

        sourceNodeIds = new int[numberOfEdges];
        targetNodeIds = new int[numberOfEdges];
        sourceToTargetCosts = new int[numberOfEdges];
    }

    public void addNode(int index, double latitude, double longitude) {
        nodes[index][0] = latitude;
        nodes[index][1] = longitude;
    }

    public void addEntry(int index, int sourceNodeId, int targetNodeId, int sourceToTargetCost) {
        sourceNodeIds[index] = sourceNodeId;
        targetNodeIds[index] = targetNodeId;
        sourceToTargetCosts[index] = sourceToTargetCost;
    }

    public double[][] getNodes() {
        return nodes;
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
        for (int i = 0; i < nodes.length; i++) {
            currentNode = nodes[i];
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


