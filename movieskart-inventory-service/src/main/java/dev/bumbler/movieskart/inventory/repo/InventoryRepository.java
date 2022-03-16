package dev.bumbler.movieskart.inventory.repo;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<MoviesInventory, Long> {
    public Optional<MoviesInventory> findByMovieId(Long movieId);
}
