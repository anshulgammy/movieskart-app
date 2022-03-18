package dev.bumbler.movieskart.model.orchestrator;

import dev.bumbler.movieskart.model.order.Order;
import java.util.List;
import org.springframework.http.HttpStatus;

public class MoviesKartResponse {

  private Long customerId;
  private List<Order> orderList;
  private List<MoviesKartMovies> moviesKartMoviesList;
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

  public List<MoviesKartMovies> getMoviesKartMoviesList() {
    return moviesKartMoviesList;
  }

  public void setMoviesKartMoviesList(List<MoviesKartMovies> moviesKartMoviesList) {
    this.moviesKartMoviesList = moviesKartMoviesList;
  }
}
