package de.unistuttgart.rest;

import de.unistuttgart.DataReader;
import de.unistuttgart.rest.errorhandling.DataReadAlreadyInProgressException;
import de.unistuttgart.rest.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ApplicationStartupProcess {

    private boolean readInProgress = false;

    @Autowired
    private ConfigurableApplicationContext ctx;

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
            Log.logErr("****************************************************");
            Log.logErr("Graph file not found. Aborting application Startup.");
            Log.logErr("****************************************************");
            ctx.close();
            return false;
        } else {
            Log.logInfo("Using germany graph for routing at " + DataStore.getFmiGraphFilePath());
        }

        dataReader.readData(graphFile);
        readInProgress = false;

        DataStore.setAdjacencyArray(dataReader.getAdjacencyArray());
        DataStore.setCoordinatesSet(dataReader.getCoordinatesSet());
        DataStore.setDataAlreadyRead();
        Log.logInfo("Data loaded successfully. Service is ready at http://localhost:" + DataStore.getApplicationPort() + ".");

        return true;
    }

}
