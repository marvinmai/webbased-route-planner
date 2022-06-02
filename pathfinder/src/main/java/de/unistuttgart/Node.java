package de.unistuttgart;

public class Node implements Comparable<Node>{

    private final int nodeID;
    private final int costs;

    Node(final int nodeID, final int costs){
        this.nodeID = nodeID;
        this.costs = costs;
    }

    @Override
    public int compareTo(Node n) {
        if(n.getCosts() < this.getCosts()){
            return 1;
        }
        else {
            return -1;
        }
    }

    public int getNodeID() {
        return nodeID;
    }

    public int getCosts() {
        return costs;
    }
}
