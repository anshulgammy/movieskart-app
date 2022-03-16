package dev.bumbler.movieskart.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model.inventory"})
public class MoviesKartInventoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartInventoryServiceApplication.class, args);
  }
}
