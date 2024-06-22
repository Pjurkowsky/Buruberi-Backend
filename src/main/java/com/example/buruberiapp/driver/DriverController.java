package com.example.buruberiapp.driver;

import com.example.buruberiapp.order.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
@Validated
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("")
    ResponseEntity<List<Driver>> getAll() {
        return ResponseEntity.ok().headers(new HttpHeaders()).body((List<Driver>) driverService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Driver>> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(driverService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Driver>> addDriver(@Valid @RequestBody Driver driver) {
        Optional<Driver> savedDriver = driverService.save(driver);
        return ResponseEntity.ok().headers(new HttpHeaders()).body(savedDriver);
    }



}
