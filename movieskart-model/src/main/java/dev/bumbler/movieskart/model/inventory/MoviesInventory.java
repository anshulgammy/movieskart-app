package dev.bumbler.movieskart.model.inventory;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MOVIES_INVENTORY")
public class MoviesInventory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long movieId;
  private Long quantityAvailable;
  private Boolean sellable;
  private Double currentPrice;

  public MoviesInventory() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getQuantityAvailable() {
    return quantityAvailable;
  }

  public void setQuantityAvailable(Long quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }

  public Boolean getSellable() {
    return sellable;
  }

  public void setSellable(Boolean sellable) {
    this.sellable = sellable;
  }

  public Double getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
  }
}
