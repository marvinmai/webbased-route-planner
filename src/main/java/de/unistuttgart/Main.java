package de.unistuttgart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/toy.fmi");
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 4; i++) {
                scanner.nextLine();
            }
            int numberOfNodes = scanner.nextInt();
            scanner.nextLine();
            int numberOfEdges = scanner.nextInt();

            scanner.nextLine();
            double[][] nodes = new double[numberOfNodes][2];
            for (int i = 0; i < numberOfNodes && scanner.hasNext(); i++) {
                scanner.nextInt();
                scanner.nextInt();
                nodes[i][0] = scanner.nextDouble();
                nodes[i][1] = scanner.nextDouble();
                scanner.nextLine();
            }
            int[] sourceNodeIds = new int[numberOfEdges];
            int[] targetNodeIds = new int[numberOfEdges];
            int[] sourceToTargetCosts = new int[numberOfEdges];
            for (int i = 0; i < numberOfEdges && scanner.hasNext(); i++) {
                sourceNodeIds[i] = scanner.nextInt();
                targetNodeIds[i] = scanner.nextInt();
                sourceToTargetCosts[i] = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}