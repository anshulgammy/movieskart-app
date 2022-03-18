package dev.bumbler.movieskart.model.orchestrator;

public class MoviesKartRequest {

  private Long customerId;
  private String movieNameKeyWord;
  private Long movieId;
  private Long orderId;
  private Long inventoryId;
  private Long orderQuantity;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getMovieNameKeyWord() {
    return movieNameKeyWord;
  }

  public void setMovieNameKeyWord(String movieNameKeyWord) {
    this.movieNameKeyWord = movieNameKeyWord;
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
  }

  public Long getOrderQuantity() {
    return orderQuantity;
  }

  public void setOrderQuantity(Long orderQuantity) {
    this.orderQuantity = orderQuantity;
  }
}
