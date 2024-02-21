package com.example.buruberiapp.price;

import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Integer> {
    Price findTopByOrderByCreatedDateDesc();
}
