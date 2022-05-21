package de.unistuttgart.rest;

import de.unistuttgart.DataReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Objects;

@RestController
public class StartupController {

    private boolean readInProgress = false;

    @GetMapping("/startup")
    public boolean startup() throws DataReadAlreadyInProgressException {
        if (readInProgress) {
            throw new DataReadAlreadyInProgressException();
        }
        readInProgress = true;
        DataReader dataReader = new DataReader();
        String graphFile;

        graphFile = System.getProperty("user.dir") + "/germany.fmi";
        File f = new File(graphFile);
        if(!f.exists() || f.isDirectory()) {
            graphFile = Objects.requireNonNull(getClass().getClassLoader().getResource("toy.fmi")).getFile();
            Log.logInfo("Using default toy graph for routing.");
        } else {
            Log.logInfo("Using germany graph for routing.");
        }

        dataReader.readData(graphFile);
        readInProgress = false;

        DataStore.setAdjacencyArray(dataReader.getAdjacencyArray());
        DataStore.setCoordinatesSet(dataReader.getCoordinatesSet());

        return true;
    }

}
