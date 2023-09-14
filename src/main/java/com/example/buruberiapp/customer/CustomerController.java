package com.example.buruberiapp.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public Iterable<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getById(@PathVariable("id") int id) {
        return customerService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Customer>> addCustomer(@RequestBody Customer customer) {
        Optional<Customer> savedCustomer = customerService.save(customer);

        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedCustomer);
    }
}
