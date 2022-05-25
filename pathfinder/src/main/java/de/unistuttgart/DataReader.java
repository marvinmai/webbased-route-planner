package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    private AdjacencyArray adjacencyArray;

    private CoordinatesSet coordinatesSet;

    private String filePath;

    public void readData(String filePath) {
        this.filePath = filePath;
        BufferedReader reader;
        try {
            FileReader fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            for (int i = 0; i < 5; i++) {
                reader.readLine();
            }

            int numberOfNodes = Integer.parseInt(reader.readLine());
            int numberOfEdges = Integer.parseInt(reader.readLine());

            coordinatesSet = parseCoordinates(reader, numberOfNodes, numberOfEdges);
            adjacencyArray = parseAdjacencyArray(reader, numberOfNodes, numberOfEdges);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    private CoordinatesSet parseCoordinates(BufferedReader reader, int numberOfNodes, int numberOfEdges) throws IOException {
        CoordinatesSet coordinates = new CoordinatesSet(numberOfNodes, numberOfEdges);

        String[] lineElements;
        for (int i = 0; i < numberOfNodes; i++) {
            lineElements = reader.readLine().split(" ");
            coordinates.addCoordinate(i, Double.parseDouble(lineElements[2]), Double.parseDouble(lineElements[3]));;
        }
        return coordinates;
    }

    private AdjacencyArray parseAdjacencyArray(BufferedReader reader, int numberOfNodes, int numberOfEdges) throws IOException {
        AdjacencyArray adjArray = new AdjacencyArray(numberOfNodes);

        String[] lineElements;
        int currentSourceNode;
        int lastSourceNode = 0;
        for (int i = 0; i < numberOfEdges; i++) {
            lineElements = reader.readLine().split(" ");
            currentSourceNode = Integer.parseInt(lineElements[0]);

            while (lastSourceNode != currentSourceNode) {
                adjArray.next();
                lastSourceNode++;
            }

            adjArray.addNode(
                    currentSourceNode,
                    Integer.parseInt(lineElements[1]),
                    Integer.parseInt(lineElements[2]));
        }
        adjArray.next();

        return adjArray;
    }

    public AdjacencyArray getAdjacencyArray() {
        return adjacencyArray;
    }

    public CoordinatesSet getCoordinatesSet() {
        return coordinatesSet;
    }
}
