package dev.bumbler.movieskart.order.service;

import static java.util.Objects.requireNonNull;

import com.google.common.base.Preconditions;
import dev.bumbler.movieskart.model.inventory.InventoryServiceResponse;
import dev.bumbler.movieskart.model.inventory.MovieInventory;
import dev.bumbler.movieskart.model.order.Order;
import dev.bumbler.movieskart.model.order.OrderServiceRequest;
import dev.bumbler.movieskart.model.order.OrderServiceResponse;
import dev.bumbler.movieskart.order.exceptions.OrderProcessingException;
import dev.bumbler.movieskart.order.exceptions.OrderServiceException;
import dev.bumbler.movieskart.order.repo.OrderRepository;
import dev.bumbler.movieskart.order.util.Constants;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

  private OrderRepository orderRepository;
  private RestTemplate restTemplate;

  @Value("${movieskart.inventory.getbymovieid}")
  private String inventoryByMovieIdURL;

  @Value("${movieskart.inventory.postinventory}")
  private String postInventoryURL;

  @Autowired
  public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
    this.orderRepository =
        requireNonNull(orderRepository, "orderRepository is required, but its missing");
    this.restTemplate = requireNonNull(restTemplate, "restTemplate is required, but its missing");
  }

  public OrderServiceResponse placeOrder(OrderServiceRequest orderServiceRequest)
      throws OrderServiceException, OrderProcessingException, RestClientException {
    Boolean orderServiceRequestValidation = validateOrderServiceRequest(orderServiceRequest);
    if (!orderServiceRequestValidation) {
      throw new OrderServiceException("Unable to process order");
    }

    InventoryServiceResponse inventoryServiceResponse =
        restTemplate.getForObject(
            inventoryByMovieIdURL + orderServiceRequest.getMovieId(),
            InventoryServiceResponse.class);

    Boolean orderValidationStatus = validateOrder(inventoryServiceResponse, orderServiceRequest);
    if (!orderValidationStatus) {
      throw new OrderServiceException("Inventory and Order mismatch. Unable to process order");
    }

    InventoryServiceResponse processedInventoryServiceResponse =
        processInventory(inventoryServiceResponse, orderServiceRequest);
    if (Objects.isNull(processedInventoryServiceResponse)) {
      throw new OrderServiceException("Unable to update Inventory");
    }

    Order processedOrder = processOrder(inventoryServiceResponse, orderServiceRequest);
    if (Objects.isNull(processedOrder)) {
      // rollbackInventory(processedInventoryServiceResponse, orderServiceRequest);
      throw new OrderServiceException("Unable to process order");
    }

    return prepareOrderServiceResponse(Arrays.asList(processedOrder));
  }

  public OrderServiceResponse getOrderById(String orderId) throws OrderServiceException {
    Preconditions.checkArgument(StringUtils.isNotEmpty(orderId));
    Optional<Order> order = orderRepository.findById(Long.valueOf(orderId));
    if (order.isPresent()) {
      return prepareOrderServiceResponse(Arrays.asList(order.get()));
    } else {
      throw new OrderServiceException("Order not found");
    }
  }

  public OrderServiceResponse getOrdersByCustomerId(String customerId)
      throws OrderServiceException {
    Preconditions.checkArgument(StringUtils.isNotEmpty(customerId));
    List<Order> orderList = orderRepository.findByCustomerId(customerId);
    if (Objects.nonNull(orderList) && orderList.size() > 0) {
      return prepareOrderServiceResponse(orderList);
    } else {
      throw new OrderServiceException("Order not found");
    }
  }

  private OrderServiceResponse prepareOrderServiceResponse(List<Order> orderList) {
    return new OrderServiceResponse(
        orderList, HttpStatus.OK, Constants.ORDER_SERVICE_SUCCESS_RESPONSE_MESSAGE);
  }

  private Boolean validateOrder(
      InventoryServiceResponse inventoryServiceResponse, OrderServiceRequest orderServiceRequest)
      throws OrderProcessingException {
    if (Objects.isNull(inventoryServiceResponse)
        || Objects.isNull(inventoryServiceResponse.getInventory())) {
      return false;
    }

    MovieInventory moviesInventory = inventoryServiceResponse.getInventory();
    if (!moviesInventory.getSellable()) {
      throw new OrderProcessingException("This item is not sellable right now. Unable to process.");
    }
    if (moviesInventory.getQuantityAvailable() < orderServiceRequest.getQuantity()) {
      throw new OrderProcessingException(
          "Quantity available in inventory is less. Unable to process.");
    }

    return true;
  }

  private InventoryServiceResponse processInventory(
      InventoryServiceResponse inventoryServiceResponse, OrderServiceRequest orderServiceRequest)
      throws OrderProcessingException {
    MovieInventory moviesInventory = inventoryServiceResponse.getInventory();

    Long newQuantityAvailable =
        moviesInventory.getQuantityAvailable() - orderServiceRequest.getQuantity();

    if (newQuantityAvailable == 0) {
      // Marking the item out of stock.
      moviesInventory.setSellable(false);
    }
    moviesInventory.setQuantityAvailable(newQuantityAvailable);

    InventoryServiceResponse postInventoryResponse =
        restTemplate.postForObject(
            postInventoryURL, moviesInventory, InventoryServiceResponse.class);

    if (Objects.nonNull(postInventoryResponse)
        && postInventoryResponse.getHttpStatus() == HttpStatus.OK) {
      return postInventoryResponse;
    } else {
      throw new OrderProcessingException("Unable to update inventory. Order processing failed.");
    }
  }

  private Order processOrder(
      InventoryServiceResponse inventoryServiceResponse, OrderServiceRequest orderServiceRequest) {
    Order order = new Order();
    order.setCustomerId(orderServiceRequest.getCustomerId());
    order.setInventoryId(inventoryServiceResponse.getInventory().getId());
    order.setMovieId(inventoryServiceResponse.getInventory().getMovieId());
    order.setQuantity(orderServiceRequest.getQuantity());
    order.setPurchaseCost(inventoryServiceResponse.getInventory().getCurrentPrice());
    order.setPurchaseDateTime(LocalDateTime.now());
    return orderRepository.save(order);
  }

  private Boolean validateOrderServiceRequest(OrderServiceRequest orderServiceRequest) {
    if (Objects.isNull(orderServiceRequest.getQuantity())
        || Objects.isNull(orderServiceRequest.getCustomerId())
        || Objects.isNull(orderServiceRequest.getInventoryId())
        || Objects.isNull(orderServiceRequest.getMovieId())) {
      return false;
    }
    return true;
  }
}
