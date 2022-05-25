package de.unistuttgart.rest;

import de.unistuttgart.DataReader;
import de.unistuttgart.rest.errorhandling.DataReadAlreadyInProgressException;
import de.unistuttgart.rest.util.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Objects;

@RestController
public class StartupController {

    private boolean readInProgress = false;

    @GetMapping("/startup")
    @EventListener(ApplicationReadyEvent.class)
    public boolean startup() throws DataReadAlreadyInProgressException {
        if (readInProgress) {
            throw new DataReadAlreadyInProgressException();
        }
        readInProgress = true;
        DataReader dataReader = new DataReader();
        String graphFile;

        graphFile = DataStore.getFmiGraphFilePath();
        File f = new File(graphFile);
        if(!f.exists() || f.isDirectory()) {
            graphFile = Objects.requireNonNull(getClass().getClassLoader().getResource("toy.fmi")).getFile();
            Log.logInfo("Using default toy graph for routing.");
        } else {
            Log.logInfo("Using germany graph for routing at " + DataStore.getFmiGraphFilePath());
        }

        dataReader.readData(graphFile);
        readInProgress = false;

        DataStore.setAdjacencyArray(dataReader.getAdjacencyArray());
        DataStore.setCoordinatesSet(dataReader.getCoordinatesSet());
        DataStore.setDataAlreadyRead();
        Log.logInfo("Data loaded successfully. Service is ready.");

        return true;
    }

}
