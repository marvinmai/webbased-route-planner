package de.unistuttgart;

import java.util.*;

public class WorkingOneToAllDijkstra {
    public int[] getDist() {
        return dist;
    }

    public Set<Integer> getSettled() {
        return settled;
    }

    public PriorityQueue<NodeForOneToAll> getPriorityQueue() {
        return priorityQueue;
    }

    private int[] dist;
    private Set<Integer> settled = new HashSet<>();

    private PriorityQueue<NodeForOneToAll> priorityQueue;
    private int numberOfNodes;

    private AdjacencyArray adjacencyArray;
    public WorkingOneToAllDijkstra(){
        priorityQueue = new PriorityQueue<NodeForOneToAll>();
    }

    public void dijktstra(AdjacencyArray adjacencyArray, int src){
        this.adjacencyArray = adjacencyArray;
        this.numberOfNodes = adjacencyArray.getNumberOfNodes();
        this.dist = new int[numberOfNodes];


        for(int i = 0; i < numberOfNodes; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        priorityQueue.add(new NodeForOneToAll(src,0));
        dist[src] = 0;
        while (settled.size() != numberOfNodes){

            if(priorityQueue.isEmpty()){
                return;
            }

            int u = priorityQueue.remove().getNodeID();

            if(settled.contains(u)){
                continue;
            }
            settled.add(u);
            adjacencyNodes(u);

        }
    }
    private void adjacencyNodes (int node){
        int edgeDistance = 0;
        int newDistance = 0;
        for(int i = 0; i < adjacencyArray.getAdjacentNodes(node).size(); i++) {
            int adjacentNodeID = adjacencyArray.getAdjacentNodes(node).get(i)[1];
            int adjacentNodeCost = adjacencyArray.getAdjacentNodes(node).get(i)[2];
            if (!settled.contains(adjacentNodeID)) {
                edgeDistance = adjacentNodeCost;
                newDistance = dist[node] + edgeDistance;

                if (newDistance < dist[adjacentNodeID]) {
                    dist[adjacentNodeID] = newDistance;
                }
                priorityQueue.add(new NodeForOneToAll(adjacentNodeID, adjacentNodeCost));
            }
        }
    }
}
