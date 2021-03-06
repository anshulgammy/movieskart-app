package dev.bumbler.movieskart.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfiguration {

  @Bean
  @LoadBalanced
  public RestTemplate orderServiceRestTemplate() {
    return new RestTemplate();
  }
}
