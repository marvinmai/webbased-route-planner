package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            FileReader fileReader = new FileReader("/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/MV.fmi");
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
            String[] lineElements;
            double[][] nodes = new double[numberOfNodes][2];
            for (int i = 0; i < numberOfNodes; i++) {
                lineElements = reader.readLine().split(" ");
                nodes[i][0] = Double.parseDouble(lineElements[2]);
                nodes[i][1] = Double.parseDouble(lineElements[3]);
            }

            int[] sourceNodeIds = new int[numberOfEdges];
            int[] targetNodeIds = new int[numberOfEdges];
            int[] sourceToTargetCosts = new int[numberOfEdges];
            for (int i = 0; i < numberOfEdges; i++) {
                lineElements = reader.readLine().split(" ");

                sourceNodeIds[i] = Integer.parseInt(lineElements[0]);
                targetNodeIds[i] = Integer.parseInt(lineElements[1]);
                sourceToTargetCosts[i] = Integer.parseInt(lineElements[2]);
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}