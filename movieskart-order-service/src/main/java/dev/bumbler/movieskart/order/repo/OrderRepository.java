package dev.bumbler.movieskart.order.repo;

import dev.bumbler.movieskart.model.order.Order;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

  public List<Order> findByCustomerId(Long customerId);
}
