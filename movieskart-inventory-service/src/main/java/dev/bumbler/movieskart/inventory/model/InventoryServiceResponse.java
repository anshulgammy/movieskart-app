package dev.bumbler.movieskart.inventory.model;

import org.springframework.http.HttpStatus;

public class InventoryServiceResponse {
    private MoviesInventory inventory;
    private HttpStatus httpStatus;
    private String message;

    public InventoryServiceResponse() {
    }

    public InventoryServiceResponse(MoviesInventory inventory, HttpStatus httpStatus, String message) {
        this.inventory = inventory;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public MoviesInventory getInventory() {
        return inventory;
    }

    public void setInventory(MoviesInventory inventory) {
        this.inventory = inventory;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
