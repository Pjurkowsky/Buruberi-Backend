package com.example.buruberiapp.payment;

import com.example.buruberiapp.email.EmailService;
import com.example.buruberiapp.order.Order;
import com.example.buruberiapp.price.Price;
import com.example.buruberiapp.price.PriceService;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@Validated
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PriceService priceService;

    @Autowired
    EmailService emailService;
    @PostMapping("/")
    ResponseEntity<String> payment(@Valid @RequestBody PaymentOrderDetailsDTO paymentOrderDetailsDTO) throws JsonProcessingException {
        String s;
        try {
            s = paymentService.paymentAuth();
        } catch (Exception e){
            s = e.getMessage();
        }
        String accessToken = new ObjectMapper().readTree(s).get("access_token").asText();

        s = paymentService.pay(accessToken, paymentOrderDetailsDTO);

        emailService.sendNewMail("lukaszwdoiwka01@gmail.com", "test", "test");

        return ResponseEntity.ok().headers(new HttpHeaders()).body(s);
    }


}
