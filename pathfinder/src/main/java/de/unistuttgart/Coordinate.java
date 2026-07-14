package de.unistuttgart;

public class Coordinate {
    
    private int nodeIndex;
    private double latitude;
    private double longitude;

    public Coordinate(int nodeIndex, double latitude, double longitude) {
        this.nodeIndex = nodeIndex;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "[" + latitude + ", " + longitude + "]";
    }
}
