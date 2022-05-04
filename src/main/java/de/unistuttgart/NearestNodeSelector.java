package de.unistuttgart;

public class NearestNodeSelector {

    private DataSet dataSet;

    public NearestNodeSelector(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public int getForCoordinates(double lat1, double long1) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        double distance;
        int nearestNodeNumber = 0;

        double currentLong;
        double currentLat;

        for (int i = 0; i < dataSet.getNodes().length; i++) {
            currentLat = dataSet.getNodes()[i][0];
            currentLong = dataSet.getNodes()[i][1];

            distance = calcDistance(lat1, long1, currentLat, currentLong);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestNodeNumber = i;
            }
        }

        return nearestNodeNumber;
    }

    private double calcDistance(double lat1, double long1, double lat2, double long2) {
        return Math.pow(Math.abs(lat2 - lat1), 2) + Math.pow(Math.abs(long2 - long1), 2);
    }

}
