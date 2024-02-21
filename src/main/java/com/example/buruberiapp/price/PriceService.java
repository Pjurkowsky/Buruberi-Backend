package com.example.buruberiapp.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public Optional<Price> getLastPrice() { return Optional.ofNullable(priceRepository.findTopByOrderByCreatedDateDesc());}

    public Optional<Price> save(Price price) {
        Price savedPrice = priceRepository.save(price);
        return Optional.of(savedPrice);
    }

}
