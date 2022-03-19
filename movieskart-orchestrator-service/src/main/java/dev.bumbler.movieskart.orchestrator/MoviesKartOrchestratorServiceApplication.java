package dev.bumbler.movieskart.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model"})
@EnableEurekaClient
public class MoviesKartOrchestratorServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartOrchestratorServiceApplication.class, args);
  }
}
