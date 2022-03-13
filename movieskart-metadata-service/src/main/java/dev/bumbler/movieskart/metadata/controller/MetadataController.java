package dev.bumbler.movieskart.metadata.controller;

import dev.bumbler.movieskart.metadata.exceptions.MovieNotFoundException;
import dev.bumbler.movieskart.metadata.model.Movie;
import dev.bumbler.movieskart.metadata.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
public class MetadataController {

    private MetadataService metadataService;

    @Autowired
    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/movie/id/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") String movieId) throws MovieNotFoundException {
        final Movie movie = metadataService.getMovieById(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movie/name/{movieTitle}")
    public ResponseEntity<List<Movie>> getMovieByTitle(@PathVariable("movieTitle") String movieTitle) throws MovieNotFoundException {
        final List<Movie> movieList = metadataService.getMovieByTitle(movieTitle);
        return ResponseEntity.ok(movieList);
    }
}
