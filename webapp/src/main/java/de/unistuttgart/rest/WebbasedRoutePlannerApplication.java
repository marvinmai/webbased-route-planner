package de.unistuttgart.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class WebbasedRoutePlannerApplication {

    public static void main(String[] args) {
        DataStore.setFmiGraphFilePath(args[0]);
        SpringApplication app = new SpringApplication(WebbasedRoutePlannerApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }
}