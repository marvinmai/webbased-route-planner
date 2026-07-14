package de.unistuttgart.rest;

import de.unistuttgart.Coordinate;
import de.unistuttgart.CoordinatesSet;
import de.unistuttgart.DijkstraOneToOne;
import de.unistuttgart.NearestCoordinateSelector;
import de.unistuttgart.rest.model.Feature;
import de.unistuttgart.rest.model.Geometry;
import de.unistuttgart.rest.model.Properties;
import de.unistuttgart.rest.model.RouteJson;
import de.unistuttgart.rest.util.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
                "Result: id=" + coordinate.getNodeIndex() + " latitude=" + coordinate.getLatitude() + " and longitude=" + coordinate.getLongitude());
        return coordinate;
    }

    @RequestMapping(value="/shortestpath", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteJson calculateShortestPath(
            @RequestParam(value = "source-node") int sourceNode,
            @RequestParam(value = "target-node") int targetNode) {
        DijkstraOneToOne dijkstraOneToOne = new DijkstraOneToOne(DataStore.getAdjacencyArray(), sourceNode, targetNode);

        Iterable<double[]> path = dijkstraOneToOne.pathTo(targetNode);
        List<Coordinate> coords = dijkstraOneToOne.translatePathToCoordinates(DataStore.getCoordinatesSet(), path);
        Log.logInfo("Calculated shortest path from source node " + coords.get(coords.size() - 1).getNodeIndex()
                + " with " + coords.get(coords.size() - 1).toString() + " to target node " + coords.get(0).getNodeIndex()
                + " with " + coords.get(0).toString());

        RouteJson routeJson = new RouteJson();
        routeJson.setType("FeatureCollection");
        Feature feature = new Feature();
        feature.setType("Feature");
        Geometry geometry = new Geometry();
        geometry.setType("LineString");
        List<List<Double>> coordinates = new ArrayList<>();

        List<Double> coordinate;
        for (Coordinate c: coords) {
            coordinate = new ArrayList<>();
            coordinate.add(c.getLongitude());
            coordinate.add(c.getLatitude());
            coordinates.add(coordinate);
        }
        geometry.setCoordinates(coordinates);
        feature.setGeometry(geometry);
        feature.setProperties(new Properties());
        List<Feature> featureList = new ArrayList<>();
        featureList.add(feature);
        routeJson.setFeatures(featureList);

        return routeJson;
    }


}
