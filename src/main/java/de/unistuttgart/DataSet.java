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
}


