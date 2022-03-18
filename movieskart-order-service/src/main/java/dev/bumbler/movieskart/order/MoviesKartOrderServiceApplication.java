package dev.bumbler.movieskart.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model.order"})
public class MoviesKartOrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartOrderServiceApplication.class, args);
  }
}
