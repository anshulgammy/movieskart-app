package dev.bumbler.movieskart.model.inventory;

import org.springframework.http.HttpStatus;

public class InventoryServiceResponse {
  private MovieInventory inventory;
  private HttpStatus httpStatus;
  private String message;

  public InventoryServiceResponse(MovieInventory inventory, HttpStatus httpStatus, String message) {
    this.inventory = inventory;
    this.httpStatus = httpStatus;
    this.message = message;
  }

  public InventoryServiceResponse() {}

  public MovieInventory getInventory() {
    return inventory;
  }

  public void setInventory(MovieInventory inventory) {
    this.inventory = inventory;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
