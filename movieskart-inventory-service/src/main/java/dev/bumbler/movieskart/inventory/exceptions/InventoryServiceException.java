package dev.bumbler.movieskart.inventory.exceptions;

public class InventoryServiceException extends Exception {

  public InventoryServiceException() {
    super();
  }

  public InventoryServiceException(String message) {
    super(message);
  }

  public InventoryServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public InventoryServiceException(Throwable cause) {
    super(cause);
  }
}
