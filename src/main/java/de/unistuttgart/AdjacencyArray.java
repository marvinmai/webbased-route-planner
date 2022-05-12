package de.unistuttgart;

import java.util.LinkedList;
import java.util.List;

public class AdjacencyArray {

    private List<double[]>[] nodes;

    private List<double[]> currentList;

    private int numberOfNodes;

    private int currentIndex;

    public AdjacencyArray(int numberOfNodes) {
        this.nodes = new LinkedList[numberOfNodes];
        currentList = new LinkedList<>();
        currentIndex = 0;
        this.numberOfNodes = numberOfNodes;
    }

    public void addNode(double srcNode, double targetNode, double cost) {
        double[] node = new double[3];
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

    public List<double[]> getAdjacentNodes(int nodeIndex) {
        return nodes[nodeIndex];
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
