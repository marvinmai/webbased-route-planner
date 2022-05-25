package de.unistuttgart;

public class Node implements Comparable {

    private final int nodeID;
    private final int costs;

    Node(final int nodeID, final int costs){
        this.nodeID = nodeID;
        this.costs = costs;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int getNodeID() {
        return nodeID;
    }

    public int getCosts() {
        return costs;
    }
}
