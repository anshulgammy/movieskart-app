package dev.bumbler.movieskart.inventory.repo;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<MoviesInventory, Long> {
  public Optional<MoviesInventory> findByMovieId(Long movieId);
}
