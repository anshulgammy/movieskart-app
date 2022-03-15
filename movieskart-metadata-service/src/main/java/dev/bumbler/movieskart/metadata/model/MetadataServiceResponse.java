package dev.bumbler.movieskart.metadata.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class MetadataServiceResponse {
    private List<Movie> movies;
    private HttpStatus httpStatus;
    private String message;

    public MetadataServiceResponse() {
    }

    public MetadataServiceResponse(List<Movie> movies, HttpStatus httpStatus, String message) {
        this.movies = movies;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
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
