package dev.bumbler.movieskart.orchestrator.util;

public final class Constants {

  private Constants() {}

  public static final String ORCHESTRATOR_SERVICE_SUCCESS_RESPONSE_MESSAGE = "Success";
  public static final String ORCHESTRATOR_SERVICE_FALLBACK_RESPONSE =
      "Problem serving your request.";

  public static final String RETRY_NAME = "movieskartResilience4JRetry";
  public static final String CIRCUIT_BREAKER_NAME = "movieskartResilience4JCircuitBreaker";
  public static final String FALLBACK_METHOD = "fallbackMoviesKartResponse";
}
