package dev.bumbler.movieskart.inventory.service;

import com.google.common.base.Preconditions;
import dev.bumbler.movieskart.inventory.exceptions.InventoryServiceException;
import dev.bumbler.movieskart.inventory.repo.InventoryRepository;
import dev.bumbler.movieskart.inventory.util.Constants;
import dev.bumbler.movieskart.model.inventory.InventoryServiceResponse;
import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryServiceResponse getInventoryByMovieId(String movieId) throws InventoryServiceException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(movieId));
        Optional<MoviesInventory> movieInventory = inventoryRepository.findByMovieId(Long.valueOf(movieId));
        if (movieInventory.isPresent()) {
            return prepareInventoryServiceResponse(movieInventory.get());
        } else {
            throw new InventoryServiceException("Inventory not found");
        }
    }

    private InventoryServiceResponse prepareInventoryServiceResponse(MoviesInventory inventory) {
        return new InventoryServiceResponse(inventory, HttpStatus.OK,
                Constants.INVENTORY_SERVICE_SUCCESS_RESPONSE_MESSAGE);
    }

    public InventoryServiceResponse postInventory(MoviesInventory inventory) throws InventoryServiceException {
        try {
            return prepareInventoryServiceResponse(inventoryRepository.save(inventory));
        } catch (Exception ex) {
            throw new InventoryServiceException("Bad Request");
        }
    }
}
