package dev.bumbler.movieskart.metadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model.metadata"})
@EnableEurekaClient
public class MoviesKartMetadataServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartMetadataServiceApplication.class, args);
  }
}
