package dev.bumbler.movieskart.metadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages={"dev.bumbler.movieskart.model.metadata"})
public class MoviesKartMetadataServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartMetadataServiceApplication.class, args);
  }
}
