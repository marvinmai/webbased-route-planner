package de.unistuttgart.rest;

import de.unistuttgart.DataReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        dataReader.readData(Objects.requireNonNull(getClass().getClassLoader().getResource("toy.fmi")).getFile());
        readInProgress = false;

        DataStore.setAdjacencyArray(dataReader.getAdjacencyArray());
        DataStore.setCoordinatesSet(dataReader.getCoordinatesSet());

        return true;
    }

}
