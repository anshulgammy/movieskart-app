package dev.bumbler.movieskart.order.exceptions;

public class OrderServiceException extends Exception {

  public OrderServiceException() {
    super();
  }

  public OrderServiceException(String message) {
    super(message);
  }

  public OrderServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public OrderServiceException(Throwable cause) {
    super(cause);
  }
}
