package de.unistuttgart;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DijkstraOneToOne {

    // edgeTo[v] = last edge on shortest s->v path
    private double[][] edgeTo;
    // distTo[v] = distance of shortest s->v path
    private double[] distTo;
    // priority queue of vertices
    private IndexMinPQ<Double> pq;

    public DijkstraOneToOne(AdjacencyArray adjArray, int start, int targetNode) {
        edgeTo = new double[adjArray.getNumberOfNodes()][];
        distTo = new double[adjArray.getNumberOfNodes()];
        pq = new IndexMinPQ<>(adjArray.getNumberOfNodes());
        for (int v = 0; v < adjArray.getNumberOfNodes(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[start] = 0.0;

        pq.insert(start, 0.0);

       whileloop: while (!pq.isEmpty()) {
            int v = pq.delMin();
            relax(adjArray, v);
            if (v == targetNode) {
                break;
            }
        }

    }

    private void relax(AdjacencyArray adjArr, int v) {
        int[][] adjNodes = adjArr.getAdjacentNodes(v);
        if (adjNodes == null) return;
        double doubleNode[];
        for (int[] node : adjNodes) {
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

    public Iterable<double[]> pathTo(int v) {
        Stack<double[]> path = new Stack<>();
        if (!hasPathTo(v)) {
            return path;
        }
        for (double[] e = edgeTo[v]; e != null; e = edgeTo[(int) getSrcNode(e)]) {
            path.push(e);
        }
        return path;
    }

    public List<Coordinate> translatePathToCoordinates(CoordinatesSet coordinatesSet, Iterable<double[]> path) {
        List<Coordinate> pathCoordinates = new ArrayList<>();

        Iterator<double[]> iter = path.iterator();
        double[] edge = iter.next();

        Coordinate coordinate = new Coordinate((int) getSrcNode(edge),
                coordinatesSet.getLatitudeFor((int) getSrcNode(edge)),
                coordinatesSet.getLongitudeFor((int) getSrcNode(edge)));
        pathCoordinates.add(coordinate);
        coordinate = new Coordinate((int) getTargetNode(edge),
                coordinatesSet.getLatitudeFor((int) getTargetNode(edge)),
                coordinatesSet.getLongitudeFor((int) getTargetNode(edge)));
        pathCoordinates.add(coordinate);

        iter.forEachRemaining(e -> {
            Coordinate c = new Coordinate((int) e[0],
                coordinatesSet.getLatitudeFor((int) getSrcNode(e)),
                coordinatesSet.getLongitudeFor((int) getSrcNode(e)));
            pathCoordinates.add(c);
        });
        return pathCoordinates;
    }

    public static int getCostsForPath(Iterable<double[]> path) {
        AtomicInteger costs = new AtomicInteger();
        if (((Stack<double[]>) path).size() == 0) {
            return -1;
        }
        path.forEach(node -> {
            costs.addAndGet((int) node[2]);
        });
        return costs.get();
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

    private double getTargetNode(double[] edge) {
        return edge[1];
    }

    private double getCost(int[] edge) {
        return edge[2];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

}
