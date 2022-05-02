package de.unistuttgart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static DataSet dataSet;
    public static void main(String[] args) {
        long start = System.nanoTime() / 1000000;

        String filePath = "/Users/aminheidari/Documents/Uni/Programmierprojekt/Phase I/bw.fmi";
        DataSetReader dataSetReader = new DataSetReader();
        dataSetReader.readDataSet(filePath);

        long duration = System.nanoTime() / 1000000 - start;
        System.out.println("Execution duration in ms: " + duration);
    }
}