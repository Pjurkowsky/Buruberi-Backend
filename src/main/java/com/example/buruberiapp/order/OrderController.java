package com.example.buruberiapp.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@Validated
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public ResponseEntity<Iterable<Order>> getAll() {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(orderService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Order>> addOrder(@Valid @RequestBody Order order) {
        Optional<Order> savedOrder = orderService.save(order);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedOrder);
    }
}
