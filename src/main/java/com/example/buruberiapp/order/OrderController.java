package com.example.buruberiapp.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
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

//    @GetMapping("/getorderinfo")
//    public ResponseEntity<Optional<OrderInfoDTO>> getNumberAndInfoAboutOrdersFromCurrentDay() {
//        return ResponseEntity.ok().headers(new HttpHeaders()).body(orderService.getNumberAndInfoAboutOrdersFromCurrentDay());
//    }


    @GetMapping("/getinfoorders")
    public ResponseEntity<Optional<Map<LocalDate,OrderInfoDTO>>> getInfoAboutOrders(){
        return ResponseEntity.ok().headers(new HttpHeaders()).body(Optional.ofNullable(orderService.getAmountAndNumberOfOrders()));
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Order>> addOrder(@Valid @RequestBody Order order) {
        Optional<Order> savedOrder = orderService.save(order);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedOrder);
    }

}
