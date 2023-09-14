package com.example.buruberiapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Iterable<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getById(int id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> save(Order order) {
        Order savedOrder = orderRepository.save(order);
        return Optional.of(savedOrder);
    }
}
