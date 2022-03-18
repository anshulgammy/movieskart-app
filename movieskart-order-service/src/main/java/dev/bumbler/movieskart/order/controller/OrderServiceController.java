package dev.bumbler.movieskart.order.controller;

import dev.bumbler.movieskart.model.order.OrderServiceRequest;
import dev.bumbler.movieskart.model.order.OrderServiceResponse;
import dev.bumbler.movieskart.order.exceptions.OrderProcessingException;
import dev.bumbler.movieskart.order.exceptions.OrderServiceException;
import dev.bumbler.movieskart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class OrderServiceController {

  private OrderService orderService;

  @Autowired
  public OrderServiceController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/orders/id/{orderId}")
  public ResponseEntity<OrderServiceResponse> getOrderById(@PathVariable("orderId") String orderId)
      throws OrderServiceException {
    final OrderServiceResponse orderServiceResponse = orderService.getOrderById(orderId);
    return ResponseEntity.ok(orderServiceResponse);
  }

  @GetMapping("/orders/customer/{customerId}")
  public ResponseEntity<OrderServiceResponse> getOrdersByCustomerId(
      @PathVariable("customerId") String customerId) throws OrderServiceException {
    final OrderServiceResponse orderServiceResponse =
        orderService.getOrdersByCustomerId(customerId);
    return ResponseEntity.ok(orderServiceResponse);
  }

  @PostMapping("/order")
  public ResponseEntity<OrderServiceResponse> placeOrder(
      @RequestBody OrderServiceRequest orderServiceRequest)
      throws OrderServiceException, OrderProcessingException {
    final OrderServiceResponse orderServiceResponse = orderService.placeOrder(orderServiceRequest);
    return ResponseEntity.ok(orderServiceResponse);
  }

  @ExceptionHandler({OrderServiceException.class})
  public ResponseEntity<OrderServiceResponse> handleOrderServiceException(Exception ex) {
    final OrderServiceResponse orderServiceResponse = new OrderServiceResponse();
    orderServiceResponse.setMessage(ex.getMessage());
    orderServiceResponse.setOrder(null);
    orderServiceResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
    return ResponseEntity.badRequest().body(orderServiceResponse);
  }

  @ExceptionHandler({OrderProcessingException.class})
  public ResponseEntity<OrderServiceResponse> handleOrderProcessingException(Exception ex) {
    final OrderServiceResponse orderServiceResponse = new OrderServiceResponse();
    orderServiceResponse.setMessage(ex.getMessage());
    orderServiceResponse.setOrder(null);
    orderServiceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return ResponseEntity.badRequest().body(orderServiceResponse);
  }
}
