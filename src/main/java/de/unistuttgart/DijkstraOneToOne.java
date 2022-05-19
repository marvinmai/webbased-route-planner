package de.unistuttgart;

import java.util.List;
import java.util.Stack;

public class DijkstraOneToOne {

    // edgeTo[v] = last edge on shortest s->v path
    private double[][] edgeTo;
    // distTo[v] = distance of shortest s->v path
    private double[] distTo;
    // priority queue of vertices
    private IndexMinPQ<Double> pq;

    public DijkstraOneToOne(AdjacencyArray adjArray, int start) {
        edgeTo = new double[adjArray.getNumberOfNodes()][];
        distTo = new double[adjArray.getNumberOfNodes()];
        pq = new IndexMinPQ<>(adjArray.getNumberOfNodes());
        for (int v = 0; v < adjArray.getNumberOfNodes(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[start] = 0.0;

        pq.insert(start, 0.0);
        while(!pq.isEmpty()) {
            relax(adjArray, pq.delMin());
        }
    }

    private void relax(AdjacencyArray adjArr, int v) {
        List<int[]> adjNodes = adjArr.getAdjacentNodes(v);
        if (adjNodes == null) return;
        double doubleNode[];
        for (int[] node: adjNodes) {
            int w = (int) getTargetNode(node);
            if (distTo[w] > distTo[v] + getCost(node)) {
                distTo[w] = distTo[v] + getCost(node);
                doubleNode = new double[]{node[0], node[1], node[2]};
                edgeTo[w] = doubleNode;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    private double getSrcNode(int[] edge) {
        return edge[0];
    }

    private double getSrcNode(double[] edge) {
        return edge[0];
    }

    private double getTargetNode(int[] edge) {
        return edge[1];
    }

    private double getCost(int[] edge) {
        return edge[2];
    }
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<double[]> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<double[]> path = new Stack <>();
        for(double[] e = edgeTo[v]; e!= null; e = edgeTo[(int)getSrcNode(e)]){
            path.push(e);
        }
        return path;
    }

}
