package dev.bumbler.movieskart.metadata.service;

import com.google.common.base.Preconditions;
import dev.bumbler.movieskart.metadata.exceptions.MovieNotFoundException;
import dev.bumbler.movieskart.metadata.model.Movie;
import dev.bumbler.movieskart.metadata.repo.MetadataRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class MetadataService {

    private MetadataRepository metadataRepository;

    @Autowired
    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public Movie getMovieById(String movieId) throws MovieNotFoundException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(movieId));
        Optional<Movie> movie = metadataRepository.findById(Long.valueOf(movieId));
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new MovieNotFoundException();
        }
    }

    public List<Movie> getMovieByTitle(String movieTitle) throws MovieNotFoundException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(movieTitle));
        List<Movie> movieList = metadataRepository.findByTitleContaining(movieTitle);
        if (Objects.nonNull(movieList) && movieList.size() > 0) {
            return movieList;
        } else {
            throw new MovieNotFoundException();
        }
    }
}
