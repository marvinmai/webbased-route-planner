package de.unistuttgart;
// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph

import java.util.*;
import java.lang.*;

public class DijkstraOneToAll {

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    public int[] dijkstra(AdjacencyArray adjacencyArray, int src) {

        int numberOfNodes = adjacencyArray.getNumberOfNodes();

        // The output array. dist[i] will hold the shortest distance from src to node with index i
        int dist[] = new int[numberOfNodes];

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[numberOfNodes];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < numberOfNodes; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        int costsFromUtoV = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < numberOfNodes - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, numberOfNodes);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < numberOfNodes; v++) {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                costsFromUtoV = getCostsForAtoB(u, v, adjacencyArray);
                if (!sptSet[v]
                        && costsFromUtoV != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + costsFromUtoV < dist[v])
                {
                    dist[v] = dist[u] + costsFromUtoV;
                }
            }
        }
        return dist;
    }

    private int getCostsForAtoB(int srcNode, int targetNode, AdjacencyArray adjacencyArray) {
        List<int[]> adjNodes = adjacencyArray.getAdjacentNodes(srcNode);

        for (int[] adjNode: adjNodes) {
            if (adjNode[1] == targetNode) {
                return adjNode[2];
            }
        }
        return 0;
    }

    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    private int minDistance(int dist[], Boolean sptSet[], int numberOfNodes) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numberOfNodes; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
}
