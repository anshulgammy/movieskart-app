package dev.bumbler.movieskart.metadata.repo;

import dev.bumbler.movieskart.model.metadata.Movie;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MetadataRepository extends CrudRepository<Movie, Long> {

  public List<Movie> findByTitleContaining(String movieTitle);
}
