package com.example.buruberiapp.price;

import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/price")
@Validated
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping("/getlast")
    public ResponseEntity<Optional<Price>> getLastPrice() {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(priceService.getLastPrice());
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Price>> addNewPrice(@Valid @RequestBody Price price) {
        Optional<Price> savedPrice = priceService.save(price);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedPrice);
    }
}
