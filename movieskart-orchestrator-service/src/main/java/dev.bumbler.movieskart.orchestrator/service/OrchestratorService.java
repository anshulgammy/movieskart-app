package dev.bumbler.movieskart.orchestrator.service;

import static java.util.Objects.requireNonNull;

import dev.bumbler.movieskart.model.inventory.InventoryServiceResponse;
import dev.bumbler.movieskart.model.metadata.MetadataServiceResponse;
import dev.bumbler.movieskart.model.orchestrator.MoviesKartMovies;
import dev.bumbler.movieskart.model.orchestrator.MoviesKartRequest;
import dev.bumbler.movieskart.model.orchestrator.MoviesKartResponse;
import dev.bumbler.movieskart.model.order.Order;
import dev.bumbler.movieskart.model.order.OrderServiceRequest;
import dev.bumbler.movieskart.model.order.OrderServiceResponse;
import dev.bumbler.movieskart.orchestrator.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrchestratorService {

  @Value("${movieskart.order.getbycustomerid}")
  private String getOrderByCustomerIdURL; //

  @Value("${movieskart.metadata.getbytitle}")
  private String getMetadataByTitleURL;

  @Value("${movieskart.metadata.getbyid}")
  private String getMetadataByIdURL;

  @Value("${movieskart.order.placeorder}")
  private String placeOrderURL;

  @Value("${movieskart.inventory.getbymovieid}")
  private String getInventoryByMovieId;

  private RestTemplate restTemplate;

  @Autowired
  public OrchestratorService(RestTemplate restTemplate) {
    this.restTemplate = requireNonNull(restTemplate, "restTemplate is required, but its missing");
  }

  public MoviesKartResponse getMoviesByName(MoviesKartRequest moviesKartRequest) {
    List<MoviesKartMovies> moviesKartMoviesList = new ArrayList<>();

    MetadataServiceResponse metadataServiceResponse =
        restTemplate.getForObject(
            getMetadataByTitleURL + moviesKartRequest.getMovieNameKeyWord(),
            MetadataServiceResponse.class);

    metadataServiceResponse
        .getMovies()
        .forEach(
            movie -> {
              MoviesKartMovies moviesKartMovie = new MoviesKartMovies();
              InventoryServiceResponse movieInventory =
                  restTemplate.getForObject(
                      getInventoryByMovieId + movie.getId(), InventoryServiceResponse.class);
              moviesKartMovie.setMovie(movie);
              moviesKartMovie.setMovieInventory(movieInventory.getInventory());
              moviesKartMoviesList.add(moviesKartMovie);
            });

    return prepareMoviesKartResponse(moviesKartRequest.getCustomerId(), null, moviesKartMoviesList);
  }

  public MoviesKartResponse placeOrder(MoviesKartRequest moviesKartRequest) {
    InventoryServiceResponse inventoryServiceResponse =
        restTemplate.getForObject(
            getInventoryByMovieId + moviesKartRequest.getMovieId(), InventoryServiceResponse.class);

    MetadataServiceResponse metadataServiceResponse =
        restTemplate.getForObject(
            getMetadataByIdURL + moviesKartRequest.getMovieId(), MetadataServiceResponse.class);

    OrderServiceRequest orderServiceRequest = new OrderServiceRequest();
    orderServiceRequest.setInventoryId(inventoryServiceResponse.getInventory().getId());
    orderServiceRequest.setMovieId(moviesKartRequest.getMovieId());
    orderServiceRequest.setCustomerId(moviesKartRequest.getCustomerId());
    orderServiceRequest.setQuantity(moviesKartRequest.getOrderQuantity());

    OrderServiceResponse orderServiceResponse =
        restTemplate.postForObject(placeOrderURL, orderServiceRequest, OrderServiceResponse.class);

    MoviesKartMovies moviesKartMovies = new MoviesKartMovies();
    moviesKartMovies.setMovie(metadataServiceResponse.getMovies().get(0));
    moviesKartMovies.setMovieInventory(inventoryServiceResponse.getInventory());

    return prepareMoviesKartResponse(
        moviesKartRequest.getCustomerId(),
        orderServiceResponse.getOrderList(),
        Arrays.asList(moviesKartMovies));
  }

  public MoviesKartResponse getAllDetailsForCustomer(Long customerId) {
    List<MoviesKartMovies> moviesKartMoviesList = new ArrayList<>();

    OrderServiceResponse orderServiceResponse =
        restTemplate.getForObject(getOrderByCustomerIdURL + customerId, OrderServiceResponse.class);

    orderServiceResponse
        .getOrderList()
        .forEach(
            order -> {
              MoviesKartMovies moviesKartMovie = new MoviesKartMovies();
              moviesKartMovie.setMovie(
                  restTemplate
                      .getForObject(
                          getMetadataByIdURL + order.getMovieId(), MetadataServiceResponse.class)
                      .getMovies()
                      .get(0));
              moviesKartMovie.setMovieInventory(
                  restTemplate
                      .getForObject(getInventoryByMovieId, InventoryServiceResponse.class)
                      .getInventory());
              moviesKartMoviesList.add(moviesKartMovie);
            });

    return prepareMoviesKartResponse(
        customerId, orderServiceResponse.getOrderList(), moviesKartMoviesList);
  }

  private MoviesKartResponse prepareMoviesKartResponse(
      Long customerId, List<Order> orderList, List<MoviesKartMovies> moviesKartMoviesList) {
    MoviesKartResponse moviesKartResponse = new MoviesKartResponse();
    moviesKartResponse.setMoviesKartMoviesList(moviesKartMoviesList);
    moviesKartResponse.setCustomerId(customerId);
    moviesKartResponse.setMessage(Constants.ORCHESTRATOR_SERVICE_SUCCESS_RESPONSE_MESSAGE);
    moviesKartResponse.setOrderList(orderList);
    moviesKartResponse.setHttpStatus(HttpStatus.OK);
    return moviesKartResponse;
  }
}
