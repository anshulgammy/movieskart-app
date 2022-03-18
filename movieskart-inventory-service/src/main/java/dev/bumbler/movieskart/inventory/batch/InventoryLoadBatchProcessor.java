package dev.bumbler.movieskart.inventory.batch;

import dev.bumbler.movieskart.model.inventory.MovieInventory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class InventoryLoadBatchProcessor implements ItemProcessor<MovieInventory, MovieInventory> {

  @Override
  public MovieInventory process(MovieInventory moviesInventory) throws Exception {
    return moviesInventory;
  }
}
