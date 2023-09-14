package com.example.buruberiapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public Iterable<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getById(@PathVariable("id") int id) {
        return orderService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Order>> addOrder(@RequestBody Order order) {
        System.out.println(order);
        Optional<Order> savedOrder = orderService.save(order);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedOrder);
    }

}
