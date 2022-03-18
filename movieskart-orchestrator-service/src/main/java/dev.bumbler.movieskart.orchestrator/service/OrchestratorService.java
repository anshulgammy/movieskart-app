package dev.bumbler.movieskart.orchestrator.service;

import static java.util.Objects.requireNonNull;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import dev.bumbler.movieskart.model.metadata.MetadataServiceResponse;
import dev.bumbler.movieskart.model.metadata.Movie;
import dev.bumbler.movieskart.model.orchestrator.IncomingRequest;
import dev.bumbler.movieskart.model.orchestrator.OutgoingResponse;
import dev.bumbler.movieskart.model.order.Order;
import dev.bumbler.movieskart.model.order.OrderServiceResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrchestratorService {

  @Value("{$movieskart.metadata.getbymovieid}")
  private String getByMovieIdURL;

  @Value("{$movieskart.metadata.getbycustomerid}")
  private String getByCustomerIdURL;

  @Value("${movieskart.metadata.getmoviebytitle}")
  private String getGetByTitleURL;

  @Value("${movieskart.order.placeorder}")
  private String placeOrderURL;

  private RestTemplate restTemplate;

  @Autowired
  public OrchestratorService(RestTemplate restTemplate) {
    this.restTemplate = requireNonNull(restTemplate, "restTemplate is required, but its missing");
  }

  public OutgoingResponse getMovies(IncomingRequest incomingRequest) {
    MetadataServiceResponse metadataServiceResponse =
        restTemplate.getForObject(
            getGetByTitleURL + incomingRequest.getMovieNameKeyWord(),
            MetadataServiceResponse.class);
    if (Objects.nonNull(metadataServiceResponse)
        && Objects.nonNull(metadataServiceResponse.getMovies())) {
      return prepareOutgoingResponse(
          incomingRequest.getCustomerId(), null, metadataServiceResponse.getMovies(), null);
    }
    return new OutgoingResponse();
  }

  /*public OutgoingResponse placeOrder(IncomingRequest incomingRequest) {
      OrderServiceRequest orderServiceRequest = new OrderServiceRequest();
      orderServiceRequest.setCustomerId(incomingRequest.getCustomerId());
      orderServiceRequest.set
      OrderServiceResponse orderServiceResponse =
              restTemplate.postForObject(placeOrderURL,
                      OrderServiceResponse.class);
      if(Objects.nonNull(orderServiceResponse) && Objects.nonNull(orderServiceResponse.getOrderList())) {
          return prepareOutgoingResponse(incomingRequest.getCustomerId(), orderServiceResponse.getOrderList(), null
                  , null);
      }
      return new OutgoingResponse();
  }*/

  public OutgoingResponse getAllDetails(Long customerId) {
    List<Movie> movieList = new ArrayList<>();
    List<Order> orderList = new ArrayList<>();
    OrderServiceResponse orderServiceResponse =
        restTemplate.getForObject(getByCustomerIdURL + customerId, OrderServiceResponse.class);
    if (Objects.nonNull(orderServiceResponse)
        && Objects.nonNull(orderServiceResponse.getOrderList())) {
      orderServiceResponse
          .getOrderList()
          .forEach(
              order -> {
                MetadataServiceResponse metadataServiceResponse =
                    restTemplate.getForObject(
                        getByMovieIdURL + order.getMovieId(), MetadataServiceResponse.class);
                movieList.addAll(metadataServiceResponse.getMovies());
                orderList.add(order);
              });
    }
    return prepareOutgoingResponse(customerId, orderList, movieList, null);
  }

  private OutgoingResponse prepareOutgoingResponse(
      Long customerId,
      List<Order> orderList,
      List<Movie> movieList,
      List<MoviesInventory> inventoryList) {
    OutgoingResponse outgoingResponse = new OutgoingResponse();
    outgoingResponse.setOrderList(orderList);
    outgoingResponse.setCustomerId(customerId);
    outgoingResponse.setInventoryList(inventoryList);
    outgoingResponse.setMovieList(movieList);
    outgoingResponse.setHttpStatus(HttpStatus.OK);
    outgoingResponse.setMessage("success");
    return outgoingResponse;
  }
}
