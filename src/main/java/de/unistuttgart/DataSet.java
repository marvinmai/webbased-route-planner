package de.unistuttgart;

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

    public void addNode(int index, double firstValue, double secondValue) {
        nodes[index][0] = firstValue;
        nodes[index][1] = secondValue;
    }

    public void addEntry(int index, int sourceNodeId, int targetNodeId, int sourceToTargetCost) {
        sourceNodeIds[index] = sourceNodeId;
        targetNodeIds[index] = targetNodeId;
        sourceToTargetCosts[index] = sourceToTargetCost;
    }
}


