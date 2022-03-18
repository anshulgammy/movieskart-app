package dev.bumbler.movieskart.metadata.controller;

import static java.util.Objects.requireNonNull;

import dev.bumbler.movieskart.metadata.exceptions.MetadataServiceException;
import dev.bumbler.movieskart.metadata.service.MetadataService;
import dev.bumbler.movieskart.model.metadata.MetadataServiceResponse;
import dev.bumbler.movieskart.model.metadata.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class MetadataServiceController {

  private MetadataService metadataService;

  @Autowired
  public MetadataServiceController(MetadataService metadataService) {
    this.metadataService =
        requireNonNull(metadataService, "metadataService is required, but its missing");
  }

  @GetMapping("/movie/id/{movieId}")
  public ResponseEntity<MetadataServiceResponse> getMovieById(
      @PathVariable("movieId") String movieId) throws MetadataServiceException {
    final MetadataServiceResponse metadataServiceResponse = metadataService.getMovieById(movieId);
    return ResponseEntity.ok(metadataServiceResponse);
  }

  @GetMapping("/movie/name/{movieTitle}")
  public ResponseEntity<MetadataServiceResponse> getMovieByTitle(
      @PathVariable("movieTitle") String movieTitle) throws MetadataServiceException {
    final MetadataServiceResponse metadataServiceResponse =
        metadataService.getMovieByTitle(movieTitle);
    return ResponseEntity.ok(metadataServiceResponse);
  }

  @PostMapping("/movie")
  public ResponseEntity<MetadataServiceResponse> postMovie(@RequestBody Movie movie) {
    final MetadataServiceResponse metadataServiceResponse = metadataService.postMovie(movie);
    return ResponseEntity.ok(metadataServiceResponse);
  }

  @ExceptionHandler({MetadataServiceException.class})
  public ResponseEntity<MetadataServiceResponse> handleException(Exception ex) {
    final MetadataServiceResponse metadataServiceResponse = new MetadataServiceResponse();
    metadataServiceResponse.setMessage(ex.getMessage());
    metadataServiceResponse.setMovies(null);
    metadataServiceResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
    return ResponseEntity.badRequest().body(metadataServiceResponse);
  }
}
