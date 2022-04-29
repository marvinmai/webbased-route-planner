package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static DataSet dataSet;
    public static void main(String[] args) {
        long start = System.nanoTime() / 1000000;
        BufferedReader reader;

        String filePath = "/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/toy.fmi";

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

            dataSet = new DataSet(numberOfNodes, numberOfEdges);

            String[] lineElements;
            for (int i = 0; i < numberOfNodes; i++) {
                lineElements = reader.readLine().split(" ");
                dataSet.addNode(i, Double.parseDouble(lineElements[2]), Double.parseDouble(lineElements[3]));;
            }

            for (int i = 0; i < numberOfEdges; i++) {
                lineElements = reader.readLine().split(" ");

                dataSet.addEntry( i,
                        Integer.parseInt(lineElements[0]),
                        Integer.parseInt(lineElements[1]),
                        Integer.parseInt(lineElements[2]));
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() / 1000000 - start;
        System.out.println("Execution duration in ms: " + duration);
    }
}