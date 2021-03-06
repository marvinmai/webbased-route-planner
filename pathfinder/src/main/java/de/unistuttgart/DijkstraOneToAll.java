package de.unistuttgart;

import java.util.*;

public class DijkstraOneToAll {

    private int[] dist;
    private Set<Integer> settled = new HashSet<>();
    private int numberOfNodes;

    private Queue<Node> priorityQueue;
    private AdjacencyArray adjacencyArray;

    public DijkstraOneToAll() {
        priorityQueue = new PriorityQueue<>();
    }

    public int[] calculateDistances(AdjacencyArray adjacencyArray, int src) {
        this.adjacencyArray = adjacencyArray;
        this.numberOfNodes = adjacencyArray.getNumberOfNodes();
        this.dist = new int[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        priorityQueue.add(new Node(src, 0));
        dist[src] = 0;
        while (settled.size() != numberOfNodes) {
            if (priorityQueue.isEmpty()) {
                break;
            }
            int u = priorityQueue.remove().getNodeID();
            if (settled.contains(u)) {
                continue;
            }
            settled.add(u);
            adjacencyNodes(u);
        }
        return dist;
    }

    private void adjacencyNodes(int node) {
        int edgeDistance = 0;
        int newDistance = 0;
        int[][] edges = adjacencyArray.getAdjacentNodes(node);
        for (int i = 0; i < edges.length; i++) {
            int adjacentNodeID = edges[i][1];
            int adjacentNodeCost = edges[i][2];
            if (!settled.contains(adjacentNodeID)) {
                edgeDistance = adjacentNodeCost;
                newDistance = dist[node] + edgeDistance;
                if (newDistance < dist[adjacentNodeID]) {
                    dist[adjacentNodeID] = newDistance;
                }
                priorityQueue.add(new Node(adjacentNodeID, newDistance));
            }
        }
    }
}
