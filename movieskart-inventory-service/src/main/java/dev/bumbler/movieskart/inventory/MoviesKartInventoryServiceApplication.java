package dev.bumbler.movieskart.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model.inventory"})
@EnableEurekaClient
public class MoviesKartInventoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartInventoryServiceApplication.class, args);
  }
}
