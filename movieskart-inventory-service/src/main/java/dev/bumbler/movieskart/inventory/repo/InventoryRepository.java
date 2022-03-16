package dev.bumbler.movieskart.inventory.repo;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<MoviesInventory, Long> {
  public Optional<MoviesInventory> findByMovieId(Long movieId);
}
