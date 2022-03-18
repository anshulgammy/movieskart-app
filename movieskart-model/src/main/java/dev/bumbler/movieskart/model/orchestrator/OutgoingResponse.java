package dev.bumbler.movieskart.model.orchestrator;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import dev.bumbler.movieskart.model.metadata.Movie;
import dev.bumbler.movieskart.model.order.Order;
import java.util.List;
import org.springframework.http.HttpStatus;

public class OutgoingResponse {

  private Long customerId;
  private List<Order> orderList;
  private List<Movie> movieList;
  private List<MoviesInventory> inventoryList;
  private HttpStatus httpStatus;
  private String message;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

  public List<Movie> getMovieList() {
    return movieList;
  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
  }

  public List<MoviesInventory> getInventoryList() {
    return inventoryList;
  }

  public void setInventoryList(List<MoviesInventory> inventoryList) {
    this.inventoryList = inventoryList;
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
