package dev.bumbler.movieskart.orchestrator.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrchestratorServiceConfiguration {

  @Bean
  @LoadBalanced
  public RestTemplate orchestratorServiceRestTemplate() {
    return new RestTemplate();
  }
}
