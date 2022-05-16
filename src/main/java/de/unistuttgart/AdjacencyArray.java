package de.unistuttgart;

import java.util.LinkedList;
import java.util.List;

public class AdjacencyArray {

    private List<int[]>[] nodes;

    private List<int[]> currentList;

    private int numberOfNodes;

    private int currentIndex;

    public AdjacencyArray(int numberOfNodes) {
        this.nodes = new LinkedList[numberOfNodes];
        currentList = new LinkedList<>();
        currentIndex = 0;
        this.numberOfNodes = numberOfNodes;
    }

    public void addNode(int srcNode, int targetNode, int cost) {
        int[] node = new int[3];
        node[0] = srcNode;
        node[1] = targetNode;
        node[2] = cost;
        currentList.add(node);
    }

    public void next() {
        if (currentIndex < numberOfNodes) {
            nodes[currentIndex] = currentList;
            currentIndex++;
            currentList = new LinkedList<>();
        }
    }

    public List<int[]> getAdjacentNodes(int nodeIndex) {
        return nodes[nodeIndex];
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
