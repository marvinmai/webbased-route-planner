package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static DataSetReader dataSetReader = new DataSetReader();
    public static void main(String[] args) {
        long start = System.nanoTime() / 1000000;

        String filePath = "/home/marvin/Documents/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi";
        DataSet dataSet = dataSetReader.readDataSet(filePath);

        long duration = System.nanoTime() / 1000000 - start;
        System.out.println("Execution duration in ms: " + duration);
    }
}