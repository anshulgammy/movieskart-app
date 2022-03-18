package dev.bumbler.movieskart.orchestrator.controller;

import dev.bumbler.movieskart.model.orchestrator.OutgoingResponse;
import dev.bumbler.movieskart.orchestrator.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class OrchestratorController {

  private OrchestratorService orchestratorService;

  @Autowired
  public OrchestratorController(OrchestratorService orchestratorService) {
    this.orchestratorService = orchestratorService;
  }

  @GetMapping("/details/customer/{customerId}")
  public ResponseEntity<OutgoingResponse> getAllDetails(
      @PathVariable("customerId") Long customerId) {
    OutgoingResponse outgoingResponse = orchestratorService.getAllDetails(customerId);
    return ResponseEntity.ok(outgoingResponse);
  }
}
