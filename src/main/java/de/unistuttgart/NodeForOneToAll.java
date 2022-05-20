package de.unistuttgart;

import java.util.Comparator;

public class NodeForOneToAll implements Comparable {
    private int nodeID;

    public int getNodeID() {
        return nodeID;
    }

    public int getCosts() {
        return costs;
    }

    private int costs;
    NodeForOneToAll(final int nodeID, final int costs){
        this.nodeID = nodeID;
        this.costs = costs;
    }



    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
