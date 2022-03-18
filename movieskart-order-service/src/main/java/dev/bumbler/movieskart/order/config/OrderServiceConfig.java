package dev.bumbler.movieskart.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

  @Bean
  public RestTemplate orderServiceRestTemplate() {
    return new RestTemplate();
  }
}
