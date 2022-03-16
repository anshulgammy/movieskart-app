package dev.bumbler.movieskart.inventory.controller;

import dev.bumbler.movieskart.inventory.exceptions.InventoryServiceException;
import dev.bumbler.movieskart.inventory.service.InventoryService;
import dev.bumbler.movieskart.model.inventory.InventoryServiceResponse;
import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class InventoryServiceController {

  private InventoryService inventoryService;

  @Autowired
  public InventoryServiceController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("/inventory/movie/id/{movieId}")
  public ResponseEntity<InventoryServiceResponse> getMovieById(
      @PathVariable("movieId") String movieId) throws InventoryServiceException {
    final InventoryServiceResponse inventoryServiceResponse =
        inventoryService.getInventoryByMovieId(movieId);
    return ResponseEntity.ok(inventoryServiceResponse);
  }

  @PostMapping("/inventory")
  public ResponseEntity<InventoryServiceResponse> postInventory(
      @RequestBody MoviesInventory inventory) throws InventoryServiceException {
    final InventoryServiceResponse inventoryServiceResponse =
        inventoryService.postInventory(inventory);
    return ResponseEntity.ok(inventoryServiceResponse);
  }

  @ExceptionHandler({InventoryServiceException.class})
  public ResponseEntity<InventoryServiceResponse> handleException(Exception ex) {
    final InventoryServiceResponse inventoryServiceResponse = new InventoryServiceResponse();
    inventoryServiceResponse.setMessage(ex.getMessage());
    inventoryServiceResponse.setInventory(null);
    inventoryServiceResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
    return ResponseEntity.badRequest().body(inventoryServiceResponse);
  }
}
