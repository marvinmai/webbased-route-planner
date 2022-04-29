package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static long currentIndex = 0;
    public static void main(String[] args) {
        long start = System.nanoTime() / 1000000;
        BufferedReader reader;
        try {
            FileReader fileReader = new FileReader("/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi");
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
                currentIndex = i;
                lineElements = reader.readLine().split(" ");
                nodes[i][0] = Double.parseDouble(lineElements[2]);
                nodes[i][1] = Double.parseDouble(lineElements[3]);
            }

            int[] sourceNodeIds = new int[numberOfEdges];
            int[] targetNodeIds = new int[numberOfEdges];
            int[] sourceToTargetCosts = new int[numberOfEdges];
            for (int i = 0; i < numberOfEdges; i++) {
                currentIndex = i;
                lineElements = reader.readLine().split(" ");

                sourceNodeIds[i] = Integer.parseInt(lineElements[0]);
                targetNodeIds[i] = Integer.parseInt(lineElements[1]);
                sourceToTargetCosts[i] = Integer.parseInt(lineElements[2]);
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println(currentIndex);
            e.printStackTrace();
        }
        long duration = System.nanoTime() / 1000000 - start;
        System.out.println("Execution duration in ms: " + duration);
    }
}