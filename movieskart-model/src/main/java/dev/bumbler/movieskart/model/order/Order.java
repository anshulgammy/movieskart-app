package dev.bumbler.movieskart.model.order;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDERS")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long customerId;
  private Long inventoryId;
  private Long movieId;
  private Long quantity;
  private LocalDateTime purchaseDateTime;
  private Double purchaseCost;

  public Order() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public LocalDateTime getPurchaseDateTime() {
    return purchaseDateTime;
  }

  public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
    this.purchaseDateTime = purchaseDateTime;
  }

  public Double getPurchaseCost() {
    return purchaseCost;
  }

  public void setPurchaseCost(Double purchaseCost) {
    this.purchaseCost = purchaseCost;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
}
