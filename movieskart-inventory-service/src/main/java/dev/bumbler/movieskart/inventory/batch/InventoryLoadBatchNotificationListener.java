package dev.bumbler.movieskart.inventory.batch;

import dev.bumbler.movieskart.inventory.repo.InventoryRepository;
import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryLoadBatchNotificationListener extends JobExecutionListenerSupport {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryLoadBatchNotificationListener(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryLoadBatchNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("BatchStatus.COMPLETED");
            final Optional<MoviesInventory> inventoryLastRow = inventoryRepository.findById(100L);
            if (inventoryLastRow.isPresent()) {
                LOGGER.info("Last Data Row Ingested is: {}", inventoryLastRow.get());
            }
        }
    }
}
