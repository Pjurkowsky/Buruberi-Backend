package com.example.buruberiapp.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    Iterable<Driver> getAll() {
        return driverRepository.findAll();
    }
    Optional<Driver> getById(int id) {
        return driverRepository.findById(id);
    }

    public Optional<Driver> save(Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        return Optional.of(savedDriver);
    }

}
