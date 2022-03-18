package dev.bumbler.movieskart.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model"})
public class MoviesKartOrchestratorServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartOrchestratorServiceApplication.class, args);
  }
}
