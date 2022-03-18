package dev.bumbler.movieskart.model.orchestrator;

import dev.bumbler.movieskart.model.inventory.MovieInventory;
import dev.bumbler.movieskart.model.metadata.Movie;

public class MoviesKartMovies {

  private Movie movie;
  private MovieInventory movieInventory;

  public MoviesKartMovies() {}

  public MoviesKartMovies(Movie movie, MovieInventory movieInventory) {
    this.movie = movie;
    this.movieInventory = movieInventory;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public MovieInventory getMovieInventory() {
    return movieInventory;
  }

  public void setMovieInventory(MovieInventory movieInventory) {
    this.movieInventory = movieInventory;
  }
}
