package com.example.buruberiapp.order;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository  extends CrudRepository<Order,Integer> {
    List<Order> findAllByCreatedDateBetween(LocalDateTime startOfDate, LocalDateTime endOfDate);
    List<Order> findAllByDeliveryDateBetween(LocalDate startOfDate, LocalDate endOfDate);

}
