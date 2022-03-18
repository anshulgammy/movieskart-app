package dev.bumbler.movieskart.model.order;

import java.util.List;
import org.springframework.http.HttpStatus;

public class OrderServiceResponse {

  private List<Order> order;
  private HttpStatus httpStatus;
  private String message;

  public OrderServiceResponse() {}

  public OrderServiceResponse(List<Order> order, HttpStatus httpStatus, String message) {
    this.order = order;
    this.httpStatus = httpStatus;
    this.message = message;
  }

  public List<Order> getOrder() {
    return order;
  }

  public void setOrder(List<Order> order) {
    this.order = order;
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
