package dev.bumbler.movieskart.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoviesKartGatewayConfig {

  @Bean
  public RouteLocator moviesKartGatewayRoutes(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            "orchestrator_route_1",
            r ->
                r.path("/details/customer/**")
                    .filters(
                        f ->
                            f.rewritePath(
                                "/details/customer/(?<RID>.*)", "/v1/api/details/customer/${RID}"))
                    .uri("http://localhost:8704/"))
        .route(
            "orchestrator_route_2",
            r ->
                r.path("/movies/search")
                    .filters(f -> f.rewritePath("/movies/search", "/v1/api/movies/search"))
                    .uri("http://localhost:8704/"))
        .route(
            "orchestrator_route_3",
            r ->
                r.path("/movies/order")
                    .filters(f -> f.rewritePath("/movies/order", "/v1/api/movies/order"))
                    .uri("http://localhost:8704/"))
        .build();
  }
}
