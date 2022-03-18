package dev.bumbler.movieskart.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"dev.bumbler.movieskart.model.order"})
@EnableEurekaClient
public class MoviesKartOrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesKartOrderServiceApplication.class, args);
  }
}
