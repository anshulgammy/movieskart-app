package dev.bumbler.movieskart.model.order;

import java.util.List;
import org.springframework.http.HttpStatus;

public class OrderServiceResponse {

  private List<Order> orderList;
  private HttpStatus httpStatus;
  private String message;

  public OrderServiceResponse() {}

  public OrderServiceResponse(List<Order> order, HttpStatus httpStatus, String message) {
    this.orderList = order;
    this.httpStatus = httpStatus;
    this.message = message;
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
}
