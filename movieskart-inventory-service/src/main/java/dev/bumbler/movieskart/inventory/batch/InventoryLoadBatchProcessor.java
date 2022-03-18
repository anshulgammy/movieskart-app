package dev.bumbler.movieskart.inventory.batch;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class InventoryLoadBatchProcessor
    implements ItemProcessor<MoviesInventory, MoviesInventory> {

  @Override
  public MoviesInventory process(MoviesInventory moviesInventory) throws Exception {
    return moviesInventory;
  }
}
