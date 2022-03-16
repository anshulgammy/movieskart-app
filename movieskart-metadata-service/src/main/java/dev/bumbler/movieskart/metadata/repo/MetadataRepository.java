package dev.bumbler.movieskart.metadata.repo;

import dev.bumbler.movieskart.model.metadata.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetadataRepository extends CrudRepository<Movie, Long> {

    public List<Movie> findByTitleContaining(String movieTitle);
}
