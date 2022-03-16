package dev.bumbler.movieskart.metadata.batch;

import dev.bumbler.movieskart.model.metadata.Movie;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MetadataLoadBatchProcessor implements ItemProcessor<Movie, Movie> {

  @Override
  public Movie process(Movie movie) throws Exception {
    return movie;
  }
}
