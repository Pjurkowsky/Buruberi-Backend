package com.example.buruberiapp.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
