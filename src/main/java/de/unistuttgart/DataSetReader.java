package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataSetReader {

    private AdjacencyArray adjacencyArray;

    private DataSet dataSet;

    public DataSet readDataSet(String filePath) {
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

            adjacencyArray = new AdjacencyArray(numberOfNodes);
            dataSet = new DataSet();
            dataSet.init(numberOfNodes, numberOfEdges);

            String[] lineElements;
            for (int i = 0; i < numberOfNodes; i++) {
                lineElements = reader.readLine().split(" ");
                dataSet.addCoordinate(i, Double.parseDouble(lineElements[2]), Double.parseDouble(lineElements[3]));;
            }

            int currentSourceNode;
            int lastSourceNode = 0;
            for (int i = 0; i < numberOfEdges; i++) {
                lineElements = reader.readLine().split(" ");
                currentSourceNode = Integer.parseInt(lineElements[0]);

                while (lastSourceNode != currentSourceNode) {
                    adjacencyArray.next();
                    lastSourceNode++;
                }

                adjacencyArray.addNode(
                        currentSourceNode,
                        Integer.parseInt(lineElements[1]),
                        Integer.parseInt(lineElements[2]));
            }
            adjacencyArray.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    public AdjacencyArray getAdjacencyArray() {
        return adjacencyArray;
    }

    public DataSet getDataSet() {
        return dataSet;
    }
}
