package de.unistuttgart.rest.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataReadAlreadyInProgressException extends Throwable {
    public DataReadAlreadyInProgressException() {
        super();
    }
}
