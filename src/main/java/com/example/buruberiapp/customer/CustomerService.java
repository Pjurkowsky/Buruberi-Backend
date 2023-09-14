package com.example.buruberiapp.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(int id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> save(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        if (optionalCustomer.isEmpty())
            return Optional.of(customerRepository.save(customer));
        return optionalCustomer;
    }
}
