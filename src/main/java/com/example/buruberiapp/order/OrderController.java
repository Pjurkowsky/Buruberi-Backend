package com.example.buruberiapp.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Optional<Map<LocalDate, OrderInfoDTO>>> getInfoAboutOrders() {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(Optional.ofNullable(orderService.getAmountAndNumberOfOrders()));
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Order>> addOrder(@Valid @RequestBody Order order) {
//
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl()
//                .queryParam("grant_type", "client_credentials")
//                .queryParam("client_id", "476723")
//                .queryParam("client_secret", "7155e4f5b59486a569007d13ce41dad1");





        Optional<Order> savedOrder = orderService.save(order);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedOrder);
    }


}
