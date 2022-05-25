package de.unistuttgart.rest;

import de.unistuttgart.Coordinate;
import de.unistuttgart.DijkstraOneToOne;
import de.unistuttgart.NearestCoordinateSelector;
import de.unistuttgart.rest.util.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathRoutingController {

    @GetMapping("/nearest-node")
    public Coordinate findNearestNode(
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude
    ) {
        if (!DataStore.isDataAlreadyRead()) {
            Log.logErr("Request stopped as Data is not yet loaded. Call of /startup necessary first.");
            throw new RuntimeException("Data was not yet loaded. Call /startup first.");
        }
        NearestCoordinateSelector nearestCoordinateSelector = new NearestCoordinateSelector(DataStore.getCoordinatesSet());
        Coordinate coordinate = nearestCoordinateSelector.getForCoordinates(latitude, longitude);
        Log.logInfo("Nearest node calculated for latitude=" + latitude + " and longitude=" + longitude + ". " +
                "Result: id=" + coordinate.getNodeIndex() + "latitude=" + coordinate.getLatitude() + " and longitude=" + coordinate.getLongitude());
        return coordinate;
    }

    @GetMapping("/shortestpath")
    public void calculateShortestPath(
            @RequestParam(value = "source-node") int sourceNode,
            @RequestParam(value = "target-node") int targetNode) {
        DijkstraOneToOne dijkstraOneToOne = new DijkstraOneToOne(DataStore.getAdjacencyArray(), sourceNode);

        Iterable<double[]> path = dijkstraOneToOne.pathTo(targetNode);
    }



}
