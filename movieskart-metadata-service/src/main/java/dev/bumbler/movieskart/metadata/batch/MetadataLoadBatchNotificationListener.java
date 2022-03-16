package dev.bumbler.movieskart.metadata.batch;

import dev.bumbler.movieskart.metadata.repo.MetadataRepository;
import dev.bumbler.movieskart.model.metadata.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MetadataLoadBatchNotificationListener extends JobExecutionListenerSupport {

    private MetadataRepository metadataRepository;

    @Autowired
    public MetadataLoadBatchNotificationListener(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataLoadBatchNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("BatchStatus.COMPLETED");
            final Optional<Movie> movieDataLastRow = metadataRepository.findById(100L);
            if (movieDataLastRow.isPresent()) {
                LOGGER.info("Last Data Row Ingested is: {}", movieDataLastRow.get());
            }
        }
    }
}
