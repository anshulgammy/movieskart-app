package dev.bumbler.movieskart.metadata.repo;

import dev.bumbler.movieskart.metadata.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetadataRepository extends CrudRepository<Movie, Long> {

    public List<Movie> findByTitleContaining(String movieTitle);
}
