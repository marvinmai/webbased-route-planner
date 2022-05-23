package de.unistuttgart.rest;

import de.unistuttgart.DijkstraOneToOne;
import de.unistuttgart.NearestCoordinateSelector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathRoutingController {

    @GetMapping("/nearest-node")
    public int findNearestNode(
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude
    ) {
        NearestCoordinateSelector nearestCoordinateSelector = new NearestCoordinateSelector(DataStore.getCoordinatesSet());
        return nearestCoordinateSelector.getForCoordinates(latitude, longitude).getNodeIndex();
    }

    @GetMapping("/shortestpath")
    public void calculateShortestPath(
            @RequestParam(value = "source-node") int sourceNode,
            @RequestParam(value = "target-node") int targetNode) {
        DijkstraOneToOne dijkstraOneToOne = new DijkstraOneToOne(DataStore.getAdjacencyArray(), sourceNode);

        Iterable<double[]> path = dijkstraOneToOne.pathTo(targetNode);

    }

}
