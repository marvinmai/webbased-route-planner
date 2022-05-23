package de.unistuttgart.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataReadAlreadyInProgressException extends Throwable {
    public DataReadAlreadyInProgressException() {
        super();
    }
}
