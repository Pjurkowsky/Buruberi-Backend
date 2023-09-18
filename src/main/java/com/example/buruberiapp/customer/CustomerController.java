package com.example.buruberiapp.customer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<Iterable<Customer>> getAll() {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(customerService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Customer>> addCustomer(@Valid @RequestBody Customer customer) {
        Optional<Customer> savedCustomer = customerService.save(customer);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedCustomer);
    }
}
