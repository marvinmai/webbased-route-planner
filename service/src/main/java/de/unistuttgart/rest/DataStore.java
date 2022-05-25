package de.unistuttgart.rest;

import de.unistuttgart.AdjacencyArray;
import de.unistuttgart.CoordinatesSet;

public class DataStore {
    private static boolean dataAlreadyRead = false;
    private static AdjacencyArray adjacencyArray;
    private static CoordinatesSet coordinatesSet;

    public static AdjacencyArray getAdjacencyArray() {
        return adjacencyArray;
    }

    public static void setAdjacencyArray(AdjacencyArray adjacencyArray) {
        DataStore.adjacencyArray = adjacencyArray;
    }

    public static CoordinatesSet getCoordinatesSet() {
        return coordinatesSet;
    }

    public static void setCoordinatesSet(CoordinatesSet coordinatesSet) {
        DataStore.coordinatesSet = coordinatesSet;
    }

    public static boolean isDataAlreadyRead() {
        return dataAlreadyRead;
    }

    public static void setDataAlreadyRead() {
        DataStore.dataAlreadyRead = true;
    }
}
