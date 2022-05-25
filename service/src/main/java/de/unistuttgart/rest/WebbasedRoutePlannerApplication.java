package de.unistuttgart.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebbasedRoutePlannerApplication {

    public static void main(String[] args) {
        DataStore.setFmiGraphFilePath(args[0]);
        SpringApplication.run(WebbasedRoutePlannerApplication.class, args);
    }
}