package dev.bumbler.movieskart.inventory.repo;

import dev.bumbler.movieskart.model.inventory.MovieInventory;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<MovieInventory, Long> {
  public Optional<MovieInventory> findByMovieId(Long movieId);
}
