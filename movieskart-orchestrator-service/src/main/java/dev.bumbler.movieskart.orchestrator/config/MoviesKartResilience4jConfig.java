package dev.bumbler.movieskart.orchestrator.config;

import dev.bumbler.movieskart.orchestrator.util.Constants;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoviesKartResilience4jConfig {

  private static final String R4J_RETRY_NAME = "resilience4j.retry.name:" + Constants.RETRY_NAME;
  private static final String R4J_RETRY_MAX_ATTEMPTS =
      "resilience4j.retry.retryService.maxRetryAttempts:3";
  private static final String R4J_RETRY_WAIT_DURATION =
      "resilience4j.retry.retryService.waitDuration:3";

  private static final String R4J_CB_NAME =
      "resilience4j.circuitbreaker.name:" + Constants.CIRCUIT_BREAKER_NAME;
  private static final String R4J_CB_WAIT_DURATION_IN_OPEN_STATE = "resilience4j.circuitbreaker.waitDurationInOpenState:3";
  private static final String R4J_CB_SLIDING_WINDOW_SIZE = "resilience4j.circuitbreaker.slidingWindowSize:2";
  private static final String R4J_CB_FAILURE_RATE_THRESHOLD = "resilience4j.circuitbreaker.failureRateThreshold:30";
  private static final String R4J_CB_FAILURE_PERMITTED_NUM_OF_CALLS_IN_HALFOPEN_STATE = "resilience4j.circuitbreaker.permittedNumberOfCallsInHalfOpenState:3";

  @Bean
  public Retry resilientServiceRetry(
      @Value("${" + R4J_RETRY_NAME + "}") String retryName,
      @Value("${" + R4J_RETRY_MAX_ATTEMPTS + "}") Integer maxAttempts,
      @Value("${" + R4J_RETRY_WAIT_DURATION + "}") Integer waitDurationSeconds) {
    final RetryConfig config =
        RetryConfig.custom()
            .maxAttempts(maxAttempts)
            .retryExceptions(RuntimeException.class)
            .waitDuration(Duration.ofSeconds(waitDurationSeconds))
            .build();
    final RetryRegistry registry = RetryRegistry.of(config);
    Retry retry = registry.retry(retryName, config);
    return retry;
  }

  @Bean
  public CircuitBreaker resilientServiceCircuitBreaker(
      @Value("${" + R4J_CB_NAME + "}") String circuitBreakerName,
      @Value("${" + R4J_CB_WAIT_DURATION_IN_OPEN_STATE + "}") Integer waitDurationSeconds,
      @Value("${" + R4J_CB_SLIDING_WINDOW_SIZE + "}") Integer slidingWindowSize,
      @Value("${" + R4J_CB_FAILURE_RATE_THRESHOLD + "}") Float failureRateThreshold,
      @Value("${" + R4J_CB_FAILURE_PERMITTED_NUM_OF_CALLS_IN_HALFOPEN_STATE + "}")
          Integer permittedNumberOfCallsInHalfOpenState) {
    final CircuitBreakerConfig config =
        CircuitBreakerConfig.custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(slidingWindowSize)
            .recordException((ex) -> ex instanceof Throwable)
            .failureRateThreshold(failureRateThreshold)
            .waitDurationInOpenState(Duration.ofSeconds(waitDurationSeconds))
            .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
            .build();
    final CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
    final CircuitBreaker circuitBreaker = registry.circuitBreaker(circuitBreakerName);
    return circuitBreaker;
  }
}
