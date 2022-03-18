package dev.bumbler.movieskart.orchestrator.controller;

import dev.bumbler.movieskart.model.orchestrator.MoviesKartRequest;
import dev.bumbler.movieskart.model.orchestrator.MoviesKartResponse;
import dev.bumbler.movieskart.orchestrator.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class OrchestratorController {

  private OrchestratorService orchestratorService;

  @Autowired
  public OrchestratorController(OrchestratorService orchestratorService) {
    this.orchestratorService = orchestratorService;
  }

  @GetMapping("/details/customer/{customerId}")
  public ResponseEntity<MoviesKartResponse> getAllDetails(
      @PathVariable("customerId") Long customerId) {
    MoviesKartResponse outgoingResponse = orchestratorService.getAllDetailsForCustomer(customerId);
    return ResponseEntity.ok(outgoingResponse);
  }

  @PostMapping("/movies/search")
  public ResponseEntity<MoviesKartResponse> getMoviesByName(
      @RequestBody MoviesKartRequest moviesKartRequest) {
    MoviesKartResponse outgoingResponse = orchestratorService.getMoviesByName(moviesKartRequest);
    return ResponseEntity.ok(outgoingResponse);
  }

  @PostMapping("/movies/order")
  public ResponseEntity<MoviesKartResponse> placeOrder(
      @RequestBody MoviesKartRequest moviesKartRequest) {
    MoviesKartResponse outgoingResponse = orchestratorService.placeOrder(moviesKartRequest);
    return ResponseEntity.ok(outgoingResponse);
  }
}
