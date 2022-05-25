package de.unistuttgart.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebbasedRoutePlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebbasedRoutePlannerApplication.class, args);
    }
}