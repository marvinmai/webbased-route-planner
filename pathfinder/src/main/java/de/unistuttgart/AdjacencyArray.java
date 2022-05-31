package de.unistuttgart;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyArray {

    private int[] nodeEdgeReferences;
    private int[][] edges;

    private int numberOfNodes;
    private int numberOfEdges;

    private int currentNodeIndex;
    private int lastNodeIndex;

    private int currentEdgeIndex;

    public AdjacencyArray(int numberOfEdges, int numberOfNodes) {
        this.nodeEdgeReferences = new int[numberOfNodes];
        this.edges = new int[numberOfEdges][3];

        currentNodeIndex = 0;
        lastNodeIndex = 0;

        currentEdgeIndex = 0;
        this.numberOfNodes = numberOfNodes;
        this.numberOfEdges = numberOfEdges;
    }

    public void addEdge(int srcNode, int targetNode, int cost) {
        currentNodeIndex = srcNode;
        if (lastNodeIndex != currentNodeIndex) {
            nodeEdgeReferences[srcNode] = currentEdgeIndex;
        }
        lastNodeIndex = srcNode;
        edges[currentEdgeIndex][0] = srcNode;
        edges[currentEdgeIndex][1] = targetNode;
        edges[currentEdgeIndex][2] = cost;
        currentEdgeIndex++;
    }

    public void next() {
        if (currentNodeIndex < numberOfNodes) {
            currentNodeIndex++;
        }
    }

    public int[][] getAdjacentNodes(int nodeIndex) {
        int numberOfEdgesForNode = 0;
        int nodeEdgeReference = nodeEdgeReferences[nodeIndex];
        int tempId = nodeEdgeReference;
        while (tempId < edges.length && edges[tempId++][0] == nodeIndex) {
            numberOfEdgesForNode++;
        }
        return Arrays.copyOfRange(edges, nodeEdgeReference, nodeEdgeReference + numberOfEdgesForNode);
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
